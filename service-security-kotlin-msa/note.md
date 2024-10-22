# api gateway

msa에서 라우터의 역할

- 인증, 인가
- 서비스 마다 통신 방법이 다를 수 있는데(tcp, http, thrift, grpc, graphql...) 데이터의 변환 역할을 함
- 로드벨런싱
- 라우팅

servlet 기반(동기)

spring gateway -> netty 기반(webflux, reactive, 비동기)

## routing convention

다음과 같은 서비스와 api가 있다고 가정하자

- 게이트 웨이 주소: http://gateway.com
- 사용자 서비스: http://a.com,
    - 내 정보 조회 api, GET http://a.com/user/me
- 주문 서비스: http://b.com
    - 주문 내역 조회 api, GET http://b.com/order/1

사용자는 아래 주소로 요청한다.

1. http://gateway.com/foo/user/me
2. http://gateway.com/bar/order/1

게이트웨이는 .com/ 다음 sub-path를 기준으로 어떤 서비스로 요청을 전달할지 정한다.

예를들어\
foo는 a.com, bar는 b.com일 경우\
foo => a.com으로 지정 /user/me 요청\
bar => b.com으로 지정 /order/1 요청

## 오미 쏏 개쩌네

http://localhost:9090/service-api/open-api/health
이렇게하면 8080 연결됨 리버스 프록시를 애플리케이션 단에서 해주구나..