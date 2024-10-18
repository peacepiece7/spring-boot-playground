# RabbitMQ

오픈 소스 메시지 브로커 소프트웨어

1. 메시지 브로커는 송 수신자간 효율적 메시지 전달 중개 역할
2. AMQP(Advanced Message Queuing Protocol) 기반 동작, 대규모 분산 시스템에서 사용되는 메시지 큐 서비스 제공
3. Producer(메시지 생정 애플리케이션)과 Consumer(메시지 소비 애플리케이션) 간의 비동기적 통신을 용이하게 함
4. Producer -> (push message) -> RabbitMQ -> (send message) -> Consumer

분산 시스템, 마이크로 서비스 아키택처, 비동기 이벤트 기반 시스템에서 사용\
ApacheMq, ApacheQpid 등 다양하다고 함

## 시작하기

[docker-compose.yaml](./rabbitmq/docker-compose.yaml) 파일 작성하고\
다음과 같이 rabbitmq 를 띄운다.

```shell
cd ./rabbitmq
docker-compose up -d
```

## rabbitmq_management (관리자 페이지) 활성화 하기

localhost:15672 들어가면 아무것도 안나오는데 기본 화면읠 띄우려면 플러그인을 추가해줘야한다.

```shell
# rabbitmq 이름 확인
docker ps

# rabbitmq 실행중인 컨테이너에 터미널로 접속
docker exec it rabbitmq-rabbitmq-1 /bin/bash

# rabbitmq-plugins 설치 되어 있는지 체크
rabbitmq-plugins --version

# rabbitmq_management 활성화
rabbitmq-plugins enable rabbitmq_management
```

이제 localhost:15672 들어가보자

## Exchange

localhost:15672 들어가면 exchange 라는게 있는데 일종의 controller 이다.

publisher 가 메시지를 발행하면 exchange 는 어떤 큐에 메시지를 넣을지 결정한다.

consumer 는 메시지를 큐에서 가저오는데, 설정에 따라 다시 넣는 것도 가능하다.

## ObjectMapper

위 어노테이션이 동작하면 object <-> json 변환시 ObjectMapper 설정이 동작한다.

## rabbitMq configuration

다음 코드를 참고하여 RabbitMq 설정을 한다.

[rabbitMq configuration](./api/src/main/java/org/delivery/api/config/rabbitmq/RabbitMqConfig.java)

## producer 설정

producer를 설정하면 queue에 메시지를 push 할 수 있다.

gradle.build 에 다음 라이브러리 추가

```text
 implementation 'org.springframework.boot:spring-boot-starter-amqp'
```

[Producer 클래스 설정](./api/src/main/java/org/delivery/api/common/rabbitmq/Producer.java)

설정이 되었으면 controller를 만들어서 producer를 사용해보자.

[health check api controller](./api/src/main/java/org/delivery/api/domain/health/HealthOpenApiController.java)

localhost:15672로 가서 Queues and Streams 탭으로 넘어가면 메시지가 큐에 추가된 것을 볼 수 있다.

## 컨트롤러 파라메터에 쓰는 어노테이션

`@RequestBody`: 요청 본문을 Java 객체로 변환할 때 사용 (JSON/XML).\
`@RequestParam`: 쿼리 파라미터나 폼 데이터를 받을 때 사용.\
`@PathVariable`: URL 경로의 변수를 처리할 때 사용.\
`@RequestHeader`: HTTP 요청 헤더 값을 받을 때 사용.\
`@CookieValue`: HTTP 요청 쿠키 값을 받을 때 사용.\
`@ModelAttribute`: 요청 파라미터를 객체로 변환할 때 사용 (폼 데이터).\
`@RequestPart`: Multipart 요청의 특정 파트를 받을 때 사용.\
`@SessionAttribute`: 세션에 저장된 데이터를 사용할 때 사용.

[retry 전략과 rabbitmq 관련 포스팅](https://minholee93.tistory.com/category/RabbitMQ) 
