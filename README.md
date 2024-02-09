# everyday-commit

## Spec
- framework: spring boot
- remittance api: toss
- infra: aws ec2
- dbms: mongoDB atlas
- orm: kmongo
- logger: logback

## Workflow
1. 매일 00:01:00 에 kafka streams 를 통해 전날 커밋한 횟수를 파악 한다.
2. 커밋한 횟수가 3회 미만 이라면 toss api 를 통해 부모님 께 10,000원씩 송금 한다.
3. 송금한 내역을 db 에 저장 한다.
4. 송금한 내역을 slack 으로 알린다.
5. 만약 송금 실패 시 slack 으로 알린다.

## How to run
- [ ] Github 에서 발급 받은 personal access token 을 환경 변수로 등록 한다.
- [ ] Toss api 를 사용 하기 위해 발급 받은 api key 를 환경 변수로 등록 한다.
- [ ] Slack webhook url 을 환경 변수로 등록 한다.
- [ ] `./gradlew bootRun` 으로 실행 한다.

## TODO
