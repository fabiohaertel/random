/*
@author hyp
Lançamento: 12/04/2013
Último update: 17/05/2016
*/

var lastNrThreads = 0;
var nThreads = 0;
var urlForum = 'http://www.hardmob.com.br/promocoes/';
var checkTimeout = 30 * 1000; // 30 segundos
var lastThreadsTitle = '<p class="ext_title">Realizando carga inicial dos tópicos...aguarde.</p>';

// Obtém o HTML do fórum via ajax GET.
function obtemDadosForum() {
	$.ajax({
		type: 'GET',
        url: urlForum,
        success: function (data) {

			// Localiza o <span> que contém o número total de tópicos
			// para fazer a contagem.
			var spanRef = $('.first_last:first', $(data));
			if (spanRef.length) {

				var aRef = spanRef.children('a');
				if (aRef.length) {

					var titulo = aRef.attr('title');
					if (titulo != undefined && titulo.length) {

						nThreads = retornaQtdThreads(titulo);

						if (nThreads == lastNrThreads) {
							chrome.browserAction.setIcon({ path: "default.png" });
						}
						else if (nThreads > lastNrThreads) {
							chrome.browserAction.setIcon({ path: "new.png" });
							chrome.browserAction.setTitle({ "title": nThreads.toString() });
						}

						// Obtém o título dos três últimos tópicos.
						lastThreadsTitle = retornaTituloUltimosTopicos(data);
						
						// Envia mensagem ao popup para setar o título
						chrome.runtime.sendMessage({
							greeting: "setLastTitle",
							title: lastThreadsTitle
						},
							function (response) {
							});

					}
				}
			}
		}
    });
}

// Retorna a quantidade total de threads, através do texto do span.
function retornaQtdThreads(titulo) {
	var posUltimoEspaco = titulo.lastIndexOf(' ');
	var nThreads = titulo.substring(posUltimoEspaco, titulo.length);
	return parseFloat(nThreads);
}

// Retorna o título dos três últimos tópico
function retornaTituloUltimosTopicos(data) {

	var titles = '';

	var threadsOl = $("#threads", $(data));
	if (threadsOl.length) {
		var idArr = new Array();
		threadsOl.children("li").each(function () {
			idArr.push($(this).attr('id'));
		});

		idArr.sort();

		// Aqui monta os três títulos
		var lastId = 0;
		var title = '';
		for (var i = 0; i <= 2; i++) {
			lastId = idArr.pop();
			title = $('#' + lastId, $(data)).find('.title').text();
			titles = titles + "<p class='ext_title'>" + title + "</p>";
		}

		return titles;
	}

	return '';
}

$(document).ready(function () {

	// Monitora mensagens recebidas da popup
	chrome.runtime.onMessage.addListener(
		function (request, sender, sendResponse) {
			if (request.greeting == "open") {
				lastNrThreads = nThreads;
				chrome.browserAction.setIcon({ path: "default.png" });
				chrome.tabs.create({ 'url': urlForum });
			}
			else if (request.greeting == "ignore") {
				lastNrThreads = nThreads;
				chrome.browserAction.setIcon({ path: "default.png" });
			}
			else if (request.greeting == "getLastTitle") {
				sendResponse({
					title: lastThreadsTitle
				});
			}
		});

	// Verifica a cada X segundos (padrão = 30)
	setInterval(function () {
		obtemDadosForum();
	}, checkTimeout);

});