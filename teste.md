# Manual Frontend Neomind
![](img/logo_neo.png) 
#### Autor
Fábio Haertel Kochhann
#### Versão
1.0.0

### Tabela de Conteúdo

<!-- toc -->

- [1. Arquitetura](#1-arquitetura)
  * [1.1 Visão Geral](#11-visao-geral)
- [2. Configuração do Ambiente](#2-configuracao-do-ambiente)
  * [2.1 NodeJS](#21-nodejs)
  * [2.2 Yarn](#22-yarn)
  * [2.3 Gulp](#23-gulp)
- [3. Compilação dos Fontes](#3-compilacao-dos-fontes)
- [4. Watchers](#4-watchers)
- [5. Criação de Aplicação AngularJS](#5-criacao-de-aplicacao-angularjs)
  * [5.1 Bundles](#51-bundles)
    + [5.1.1 Definição de um Bundle](#511-definicao-de-um-bundle)
    + [5.1.2 Componentes](#512-componentes)
    + [5.1.3 Tasks Gulp](#513-tasks-gulp)
    + [5.1.4 Watchers](#514-watchers)
    + [5.1.5 Aplicação gerada](#515-aplicacao-gerada)
  * [5.2 Webpack](#52-webpack)
- [6. Internacionalização (I18n)](#6-internacionalizacao-i18n)
- [7. Considerações Finais](#7-consideracoes-finais)

<!-- tocstop -->

[TOC]

# 1. Arquitetura
## 1.1 Visão Geral
Explicar que existe o padrão antigo, com os fontes importados diretamente do webapp/js, e os padrões novos, com fontes gerados e concatenados no arquivo app.js (GED) ou gerados separadamente para cada app angular (bundles).
# 2. Configuração do Ambiente
Explicar quais ferramentas devem ser instaladas localmente pra fazer build nos fontes frontend.
## 2.1 NodeJS
## 2.2 Yarn
## 2.3 Gulp
# 3. Compilação dos Fontes
Explicar como compilar os fontes do frontend GED/Dashboard/Wizard Form Externo
# 4. Watchers
Explicar como executar os watchers do ged.
# 5. Criação de Aplicação AngularJS
Explicar passo a passo como criar, compilar e importar uma aplicação angular nova, usando o mais novo padrões(bundles) e futuros padrões depois (Webpack)
## 5.1 Bundles
### 5.1.1 Definição de um Bundle
### 5.1.2 Componentes
### 5.1.3 Tasks Gulp
### 5.1.4 Watchers
### 5.1.5 Aplicação gerada
## 5.2 Webpack
TODO
# 6. Internacionalização (I18n)
Explicar que cada app angular pode ter um m󤵬o de i18n separado. Dizer como criar os .java e como importar no bloco config da app angular.
# 7. Considerações Finais
