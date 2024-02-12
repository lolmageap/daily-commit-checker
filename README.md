# check-commit-scheduler

## Spec
- language: kotlin
- framework: spring boot, webflux
- sms: coolsms
- infra: aws ec2
- error message: slack

## Workflow
1. 매일 00:01:00 에 scheduler 를 통해 전날 커밋한 횟수를 파악 한다.
2. 커밋한 횟수가 특정 회수 미만 이라면 부모님 혹은 여자친구 중 랜덤으로 문자를 발송한다.
3. 문자 내용은 "전날에 공부를 하지 않았기 때문에 밥을 산다" 는 내용이다.
4. 문자 발송이 실패했다면 slack 으로 알린다.

## How to run
- [ ] Github 에서 발급 받은 personal access token 을 환경 변수로 등록 한다.
- [ ] coolsms api 를 사용 하기 위해 발급 받은 api key 를 환경 변수로 등록 한다.
- [ ] Slack webhook url 을 환경 변수로 등록 한다.
- [ ] `./gradlew bootRun` 으로 실행 한다.

## TODO
- 사업자등록을 하지 않는다면 송금이나 선물하기 api 기능을 사용할 수 없다.
- 추후에 상황이 된다면 문자 발송에서 송금이나 선물하기 기능으로 수정하자.
