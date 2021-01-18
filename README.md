# Event-Go

Este projeto consiste na criação de um app para gerenciar os eventos pessoais que o usuário deseja comparecer, assim confirmando check-in 
em eventos que possam surgir além de ter conhecimento dos eventos que possam aparecer

## Funcionalidades

<p>Ao visualizar os eventos disponíveis e entrar em um que desejar, é possível atualmente realizar ações como
- Compartilhar
- Localizar
- Check-in
É possível compartilhar os eventos para os amigos atráves das plataformas disponíveis, localizar o evento, a partir das coordenadas dos eventos a partir do google maps
e tambem realizar o Check-in, ao realizar, o seu nome e e-mail informado na tela de login são enviados a API para a conclusão do check-in</p>

### Layouts
Login             |  Eventos    | Detalhes do Evento 
:-------------------------:|:-------------------------:|:-------------------------:
<img src="https://github.com/NewtonCesarRoncari/eventgo/blob/main/images/screen_logo.jpg" width="250" height="420" title="Tela de Login"/> | <img src="https://github.com/NewtonCesarRoncari/eventgo/blob/main/images/screen_events.jpg" width="250" height="420" title="Tela Eventos"/> | <img src="https://github.com/NewtonCesarRoncari/eventgo/blob/main/images/screen_event_detail.jpg" width="250" height="420" title="Tela de Detalhe do Evento"/>

- Caso queira, é possível realizar o <a href="https://github.com/NewtonCesarRoncari/eventgo/blob/main/apk/eventgo.apk">download da apk<a/> 

### Bibliotecas

- Material Design
Inicialmente para fins de design foram utilizados as métricas e indicações do Material Design Component para uma melhor visualização e disposição dos componentes utilizados
- Navigation
Como a arquitetura do App foi desenvolvida em cima do MVVM (Model, View, View-Model) e em conjunto com o desenvolvimento single-activity foi escolhido a biblioteca Navigation
do Android Jetpack para flexibilizar a codificação da navegação do aplicativo
- LiveData
Como já explicado em relação a arquitetura MVVM, uma das soluções abordadas foram as LiveDatas, assim otimizando as respostas e o tráfego de informação no aplicativo desenvolvido
- Koin
Atrelado a todos os componentes antes escolhidos para o desenvolvimento da aplicação, biblioteca Koin que é uma biblioteca já desenvolvida em Kotlin para injeção de dependência
foi a escolhida por ter uma configuração otimizada e de manuseio mais performático no momendo da codificação
- Glide
Para o carregamento de imagens, levando em consideração o grau de complexidade do projeto foi escolhida a biblioteca Glide, o que consiste de maneira simples o carregamento de imagens
- Retrofit e Jackson
Levando em considerações as requisições via Json e na variações de objetos e DTOs, foi selecionado para o desenvolvimento, a bilioteca Retrofit em 
conjunto com Jackson para a manipulação dos objetos em objetos Json

## Rodando a aplicação

Clone ou realize o download do projeto em formato Zip

### Pré requisitos

Para garantir o bom funcionamento da aplicação rode com: 
- Target JVM 1.8 
- Android Gradle Plugin Version 4.1.1 
- Gradle Version 6.5.1

### Instalando 

Após clonar o projeto, importe no seu Android Studio, aceitando as susjestões da Ide, os pré requisitos serão importados automaticamente,

- Rode a aplicação normalmente

## Tecnologias utilizadas

- <a href="https://developer.android.com/guide/topics/ui/look-and-feel?hl=pt-br">Material Design<a/> 
- <a href="https://developer.android.com/guide/navigation?gclid=Cj0KCQiAvJXxBRCeARIsAMSkAppbYUXuaVm-tnHPOV9rH5RlVVScLrsUnhHxK-tbmHkYdTBeCDqU6aoaAphrEALw_wcB">Android Navigation</a>
- <a href="https://github.com/airbnb/lottie-android">Lotties</a>
- <a href="https://developer.android.com/topic/libraries/architecture/livedata">Live Data with ViewModel<a/>
- <a href="https://insert-koin.io/">Koin dependency injection<a/>
- <a href="https://github.com/bumptech/glide">Glide <a/>
- <a href="https://square.github.io/retrofit/">Retrofit<a/>
