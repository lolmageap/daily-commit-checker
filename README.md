# check-commit-scheduler

## Spec
- language: kotlin
- framework: spring boot, webflux
- scheduler: spring scheduler
- sms: coolsms
- infra: aws ec2
- error message: slack

## Workflow
1. 매일 00:01:00 에 scheduler 를 통해 전날 커밋한 횟수를 파악 한다.
2. 커밋한 횟수가 특정 회수 미만 이라면 주변 지인에게 랜덤 으로 밥 산다는 문자를 발송 한다.
3. 매주 월요일 일주일에 한번 블로그를 썼는지 체크하고 블로그를 쓰지 않았다면 주변 지인에게 밥 산다는 문자를 발송한다.
4. 문자 발송이 실패 했다면 slack 으로 알린다.

## How to run
1. Github 에서 발급 받은 personal access token 을 yml file에 등록 한다.
1. Velog의 username을 설정 파일에 등록한다.
1. 암호화 알고리즘과 password를 환경 변수로 등록한다.
1. coolsms api 를 사용 하기 위해 발급 받은 api key 를 설정 파일에 등록 한다.
1. 자신의 휴대폰 번호와 문자를 받을 수 있는 번호를 설정 파일에 등록 한다.
1. Slack webhook url 을 설정 파일에 등록 한다.
1. test 경로에 [EncodeEnvironment.kt](src%2Ftest%2Fkotlin%2Fcom%2Fexample%2Ffilialscheduler%2FEncodeEnvironment.kt) 파일에 암호화 알고리즘과 password, 위에서 등록한 환경 변수들을 등록 한 뒤 실행한다.
1. 실행이 완료 되면 encode 된 값들을 yml 파일에 등록 한다.
1. `./gradlew bootRun` 으로 실행 한다.

## TODO
- 사업자 등록을 하지 않는 다면 송금 이나 선물 하기 api 기능을 사용할 수 없다.
- 추후에 상황이 된다면 송금 이나 선물 하기 기능을 추가 하자.
