document.getElementById("open").onclick = function (e) {
    chrome.runtime.sendMessage({
        greeting: "open"
    },
        function (response) {
        });
}

document.getElementById("ignore").onclick = function (e) {
    chrome.runtime.sendMessage({
        greeting: "ignore"
    },
        function (response) {
        });
}

// Ao abrir a popup, atualiza os últimos títulos.
window.onload = function()
{
     chrome.runtime.sendMessage({
        greeting: "getLastTitle"
    },
        function (response) {
            document.getElementById("ultimo_span").innerHTML = response.title;
        });
}

// Monitora mensagens recebidas do background
chrome.runtime.onMessage.addListener(
    function (request, sender, sendResponse) {
        if (request.greeting == "setLastTitle") {
            document.getElementById("ultimo_span").innerHTML = request.title;
        }
    });