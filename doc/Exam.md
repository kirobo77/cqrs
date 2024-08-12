캐시분산서비스 구성의 성능 병목 해결 및 세션 데이터 공유
서비스메쉬서비스간 연계호출 관리
비동기 메시징서비스간 비동기통신/ 데이터 동기화 기반
데이터스토어클라우드지향 분산데이터 저장소
CI/CD 자동화소스통합 및 배포(CI/CD)
통합 모니터링분산환경 서비스 모니터링 /장애추적
컨테이너/VM가상환경 구성/관리
BFF 정의
Backend For Frontend의 약어로, Frontend 지원을 위한 Microservice들의 집합
BFF 레이어 구성기준
화면처리 항목과 디바이스 유형에 따라 분리(예: PC용 화면과 모바일 화면)
메뉴의 계층 구조를 기반으로 식별 1)
화면 UI  서비스 요청처리 및  분산된 Backend 서비스와 interaction:  화면 고유의 서비스 처리 요청을 담당하고, Backend 서비스와 Interact.
Composition:  화면에서 조회 요청 시 기존 규모가 큰 서비스를 작은 Microservice로 분산된 것을 순차 처리 및 결과를 조립하여 화면 UI에 전달.  
라우팅:  화면UI 요청 서비스가 VM기반 분산 어플리케이션인 서비스인 경우 라우팅(분기) 기능. 
SSO 연동 : SSO  서비스를 호출하여 ID 체크로 1차 인증
파일접근: UI에서 파일 Upload /Download시 파일 접근
세션 스토어 : 사용자정보 저장/변경
데이터베이스 분산
어플리케이션의 분산 영역 기준으로 DB도 분산 구성을 원칙으로 함
상호 연관성에 따라 복수 어플리케이션이 데이터를 상호 참조 할 수 있음
RDBMS
업무특성 : 트랜잭션(데이터 정합성) 보장 필요, 패키지 솔루션 제약 등
적용기준 : 단순 조회업무는 오픈RDBMS(MariaDB), 동시변경 영역은 Oracle 사용

MongoDB (문서 스토어)
업무특성 : 데이터 모델이 복잡하고 변경이 많은 영역
기대효과: 개발 생산성 향상과 개발 L/T감소로 시장 대응 속도 향상
Cassandra (칼럼 스토어)
업무특성 : 대용량 데이터 처리량 증가에 대한 대비가 필요한 영역
기대효과: 향후 가입자 증가에 대비한 수평 성능 확장이 용이
=====
차세대 시스템 아키텍처를 제안하기 위해, 현행 시스템의 주요 문제점들을 분석하고, 각 문제점에 대한 솔루션을 제시합니다.



### 1. 트랜잭션 과부하를 고려한 대고객 시스템 설계 개편
문제점:
- 대고객 시스템에서 발생하는 트랜잭션이 급증하면서, 웹 서버와 애플리케이션 서버의 성능 저하, 트랜잭션 처리 지연 및 오류가 발생하고 있음.
- 높은 동시 사용자 수와 대량의 데이터 처리 요구로 인해, 시스템이 안정적인 서비스를 제공하지 못하고 있음.

해결 방안:
- 마이크로서비스 아키텍처 도입: 단일 모놀리식 애플리케이션에서 여러 개의 마이크로서비스로 분리하여, 각 서비스가 독립적으로 확장 및 배포될 수 있도록 설계합니다. 이를 통해 특정 서비스에서 트랜잭션 부하가 발생해도 다른 서비스에 영향을 미치지 않게 됩니다.

- Auto-Scaling 및 로드 밸런싱: 클라우드 기반의 자동 확장(Auto-Scaling) 및 로드 밸런싱 기능을 도입하여 트래픽 급증 시 자동으로 서버 인스턴스를 추가하여 부하를 분산시킵니다.

- API Gateway 도입: API Gateway를 통해 클라이언트 요청을 라우팅하고, 각 요청에 대한 인증, 로깅, 모니터링 등을 수행함으로써 대고객 서비스의 안정성을 높입니다.

- 캐싱 및 CDN 활용: 자주 사용되는 정적 자원이나 데이터에 대해 캐싱을 적용하고, 글로벌 사용자에게는 CDN(Content Delivery Network)을 통해 콘텐츠를 배포함으로써 서버 부하를 감소시킵니다.

### 2. DB 과부하를 고려한 백오피스 시스템 설계 개편
문제점:
- 백오피스 시스템에서 다수의 비즈니스 로직이 DB에 집중되어 있어, DB의 과부하가 발생하고 있으며, 이로 인해 쿼리 성능 저하와 데이터베이스 잠금 현상이 발생하고 있음.
- 백오피스 사용자 수가 증가함에 따라 DB 리소스 사용량이 급증하여 성능 이슈가 발생하고 있음.

해결 방안:
- CQRS(Command Query Responsibility Segregation) 패턴 도입: 쓰기 작업(Commands)과 읽기 작업(Queries)을 분리하여, 읽기 전용 데이터베이스를 별도로 운영함으로써 DB의 부하를 분산시킵니다. 이를 통해 백오피스 시스템이 더 많은 읽기 요청을 효율적으로 처리할 수 있습니다.
- 데이터베이스 샤딩 및 파티셔닝: 데이터를 물리적으로 분산시켜 저장하는 샤딩(Sharding) 및 파티셔닝(Partitioning) 기법을 도입하여 DB의 성능을 개선합니다. 이를 통해 특정 샤드나 파티션에 대한 쿼리 부하를 분산시킬 수 있습니다.
- 비즈니스 로직의 서비스 레이어로 이전: 가능한 비즈니스 로직을 DB에서 애플리케이션 레이어로 이동시켜 DB의 로드를 줄입니다. DB는 최대한 간단한 데이터 저장소 역할만 하도록 유지합니다.
- 비동기 처리 도입: 데이터 업데이트나 장시간 실행되는 작업을 비동기 메시징 시스템(예: RabbitMQ, Kafka)을 통해 처리하여 DB의 부하를 줄이고, 백오피스 시스템의 응답 속도를 향상시킵니다.

### 3. 확장성을 고려한 Kafka 기반의 연계 표준 내용 검토
문제점:
- Kafka를 활용한 시스템 간 데이터 연계 시, 메시지의 처리량 증가에 따라 확장성이 떨어지는 문제와 메시지 유실, 중복 처리 등의 이슈가 발생할 수 있음.
- Kafka 클러스터 구성과 운영 관리의 복잡성이 증가하여 유지보수 비용이 상승하고 있음.

해결 방안:
- 파티션 전략 최적화: Kafka의 파티션 설계를 최적화하여 메시지 처리를 병렬화하고, 각 컨슈머 그룹이 적절히 파티션을 분배받아 처리할 수 있도록 합니다. 이를 통해 메시지 처리의 확장성을 확보합니다.
- 백프레셔(Backpressure) 관리: Kafka와 연계된 시스템에서 메시지 처리 속도에 차이가 발생할 경우, 백프레셔 관리 전략을 도입하여 시스템이 안정적으로 메시지를 처리할 수 있도록 조정합니다.
- 장애 복구 및 데이터 일관성 관리: 메시지 중복 처리나 유실을 방지하기 위해, 메시지 처리 시 Idempotency(멱등성) 전략을 적용하고, 필요 시 메시지 재처리를 위한 보상 트랜잭션(Business Compensation) 패턴을 도입합니다.
- 모니터링 및 알림 시스템 강화: Kafka 클러스터의 성능 및 장애 상태를 실시간으로 모니터링하고, 이상 발생 시 자동으로 알림을 보내도록 시스템을 강화합니다. Prometheus, Grafana 등을 사용해 시각화된 대시보드를 운영할 수 있습니다.
- 클라우드 네이티브 Kafka 관리: Kafka 클러스터를 클라우드 기반으로 운영하고, 필요에 따라 관리형 Kafka 서비스(예: Confluent Cloud, AWS MSK)를 활용하여 운영 및 유지보수 부담을 줄입니다.
이러한 방안을 통해 트랜잭션 및 DB 과부하 문제를 해결하고, 확장성 있는 Kafka 기반 연계 시스템을 구축할 수 있습니다.

오픈소스 카프카(Apache Kafka)는 분산 스트리밍 플랫폼으로, 대용량의 데이터를 실시간으로 처리하고, 높은 처리량과 안정성을 제공하는 메시징 시스템입니다. Apache Kafka의 장단점과 활용 범위를 정리하면 다음과 같습니다.

### 장점
1. 높은 처리량과 성능:
   - 카프카는 초당 수백만 건의 메시지를 처리할 수 있는 능력을 가지고 있어, 대규모 실시간 데이터 스트리밍과 분석에 적합합니다.
2. 확장성:
   - 카프카는 분산 시스템으로 설계되어 있어, 클러스터를 쉽게 확장할 수 있습니다. 노드를 추가하거나 삭제하는 것이 용이하며, 데이터 처리 능력의 확장성도 매우 뛰어납니다.
3. 내구성:
   - 카프카는 데이터를 디스크에 저장하고 복제본을 유지해 데이터의 손실을 방지합니다. 이로 인해 시스템이 일부 장애를 겪더라도 데이터 손실 없이 복구할 수 있습니다.
4. 유연한 데이터 소비:
   - 데이터는 다양한 방식으로 소비될 수 있으며, 카프카의 토픽(Topic)을 사용하면 여러 애플리케이션이 같은 데이터를 실시간으로 소비할 수 있습니다.
5. 실시간 데이터 처리:
   - 카프카는 실시간으로 데이터를 처리하고 스트리밍할 수 있어, 실시간 분석, 모니터링, 데이터 처리에 적합합니다.
6. 강력한 커뮤니티 및 생태계:
   - Apache Kafka는 오픈소스 프로젝트로, 전 세계 개발자들이 참여하고 있으며, 활발한 커뮤니티 지원을 받고 있습니다. 다양한 오픈소스 도구 및 프레임워크와 통합이 잘 되어 있습니다.

### 단점
1. 운영 복잡성:
   - 카프카 클러스터의 설정, 관리, 모니터링이 복잡할 수 있습니다. 특히 대규모 환경에서는 유지 보수와 운영에 상당한 경험과 노력이 필요합니다.
2. 데이터 유실 가능성:
   - 잘못된 설정이나 클러스터 장애 시 데이터 유실의 가능성이 있습니다. 적절한 설정과 데이터 복제 정책이 중요합니다.
3. 높은 학습 곡선:
   - 카프카를 처음 접하는 사용자에게는 개념과 설정이 복잡하게 느껴질 수 있습니다. 이해하고 제대로 활용하기 위해서는 시간이 필요합니다.
4. 브로커 간 불균형 문제:
   - 클러스터 내의 브로커 간에 데이터가 고르게 분포되지 않을 수 있으며, 이로 인해 특정 브로커에 과부하가 걸리는 문제가 발생할 수 있습니다.
### 활용 범위
1. 실시간 데이터 스트리밍:
   - 카프카는 실시간으로 대규모 데이터를 스트리밍하는 데 적합합니다. 예를 들어, 로그 데이터 분석, 실시간 사용자 행동 분석, 실시간 금융 거래 모니터링 등에 사용됩니다.
2. 데이터 파이프라인 구축:
   - 데이터 소스에서 여러 데이터 저장소나 처리 시스템으로 데이터를 전달하는 파이프라인을 구축하는 데 사용됩니다. 이는 데이터 웨어하우스, NoSQL 데이터베이스, 분석 시스템 등과 통합할 수 있습니다.
3. 이벤트 소싱:
   - 이벤트 기반 시스템에서 이벤트를 저장하고 처리하는 데 카프카가 사용됩니다. 이는 마이크로서비스 아키텍처에서 주로 사용되며, 각 서비스 간의 데이터 일관성을 유지하는 데 유용합니다.
4. 메시지 브로커:
   - 카프카는 메시지 브로커로서, 다양한 애플리케이션 간에 메시지를 교환하는 데 사용될 수 있습니다. 특히 높은 처리량과 내구성이 필요한 경우에 적합합니다.
5. 실시간 분석 및 모니터링:
   - 카프카는 실시간 데이터 분석, 모니터링 및 알림 시스템에 사용됩니다. 이는 IT 인프라, IoT 센서 데이터, 소셜 미디어 스트림 등의 실시간 데이터를 분석하는 데 유용합니다.
6. 로그 및 이벤트 수집:
   - 다양한 서버에서 생성된 로그와 이벤트 데이터를 중앙화된 시스템으로 수집하여 처리하는 데 활용됩니다. 이를 통해 로그 데이터를 분석하거나, 문제를 모니터링하는 데 사용할 수 있습니다.
### 결론
Apache Kafka는 실시간 데이터 스트리밍과 처리에 있어 강력한 도구로, 높은 처리량과 확장성을 제공하여 다양한 산업 분야에서 널리 활용됩니다. 그러나 운영의 복잡성과 초기 학습 곡선이 높은 편이므로, 사용 전에 충분한 학습과 계획이 필요합니다.



-----



# SAGA Pattern [7080AD1]

## 개요

### Transaction 이슈

MSA 아키텍처를 구성하기 어려운 이유 중 하나는 Transaction 문제이다 .

기존의 Monolithic환경에서는 DBMS가 기본적으로 제공해 주는 Transaction 기능을 통해서 데이터 commit이나 Rollback을 통해서 일관성 있게 관리하였다.

그러나 MSA 아키텍처에서 Application과 DB가 분산 되면서 해당 Transaction 처리를 단일 DBMS에서 제공하는 기능으로는 해결이 불가능하다.

 

### Two-Phase Commit (2PC)방안 

여러 서비스간에 데이터 일관성을 유지하기 위해서 전통적인 방법인 Two-Phase Commit 과 같은 방법을 활용하였다.

2PC는 일반적인 싱글 노드 트랜잭션에 존재하지 않는 새로운 컴포넌트인 트랜잭션 매니저를 사용한다.

2PC 트랜잭션은 애플리케이션이 여러 데이터베이스 노드들에 읽고 쓰면서 시작하게 되는데 애플리케이션이 커밋 할 준비가 되면 트랜잭션 매니저는 phase 1을 시작하며 각 노드는 prepare 요청을 보내어 커밋 할 수 있는지 질의한다.

트랜잭션 매니저는 이후 각 노드의 응답을 추적한다.

#### Two-Phase Commit의 단점 

·     

o   

- 하나의 서비스가 장애가 있는 경우나       각각의 서비스에 동시에 Rocking이 걸리게 되면 성능의 문제가 발생한다.
- 또한 각각의 서비스가 다른 instance에 있기 때문에 이를 통제하는데 어려움이 있다.

 

### SAGA 패턴의 정의 

MSA 아키텍처 환경에서 Two-Phase Commit 의 문제를 해결하기 위해서 등장하였다.

SAGA 패턴이란 마이크로서비스들끼리 이벤트를 주고 받아 특정 마이크로 서비스에서 작업이 실패하면 이전까지의 작업이 완료된 마이크로서비스들에게 보상(compensating) 이벤트를 소싱함으로써 분산 환경에서 원자성(atomicity)을 보장하는 패턴이다.

 

### SAGA 패턴의 종류

#### Choreography Based SAGA 

·     

o   

- 각각의 로컬 트랜잭션이 다른 Micro 서비스를 이벤트로 소싱하는 방식 

§ 만약 그 다음 수행해야할 트랜잭션이 있으면 해당 트랜잭션을 수행해야하는 애플리케이션으로 이벤트를 보내고 해당 애플리케이션은 완료 이벤트를 수신 받고 다음 작업을 진행하며 이를 순차적으로 수행함 

장점 

- 구성하기 편하다. 
- 도메인간 종속성이 낮다.
- 간단한 트랜잭션 업무에 적합하다.

단점 

- 트랜잭션이 많은 서비스의 경우 문제        상황 발생시 거래 추적이 어렵다.
- 즉 운영자 입장에서 트랜잭션의 현재        상태를 확인하기 어렵다. 

이때 이벤트는 kafka와 같은 메시지 큐를 통해서 비동기 방식으로 전달할 수 있다 .

![img](D:\GitHub\cqrs\doc\assets2\clip_image002.png)

 

![img](D:\GitHub\cqrs\doc\assets2\clip_image004.png)

 

#### Orchestration based SAGA

·     

o   

- Orchestration       패턴은 트랜잭션 처리를 위해 Saga 인스턴스(Manager)가 별도로 존재한다.
- 트랜잭션에 관여하는 모든 애플리케이션은 Manager에 의해서 점진적으로 트랜잭션을 수행하며 결과를        Manager에 전달하게 된다.
- 비니지스 로직상 마지막 트랜잭션이       끝나면 Manager를 종료해서 전체 트랜잭션을 처리를 종료한다.
- 만약 중간에 실패하게 되면 Manager에서 보상 트랜잭션을 발동하여 일관성을 유지한다.

§ Orchestration based Saga 패턴은 모든 관리를 Manager가 호출하기 때문에 분산 트랜잭션의 중앙 집중화가 이루어진다.

장점 

- 트랜잭션의 현재 상태를 Manager가 알고 있으므로 어떤 일이 언제 발생하는지 제어하며 이를 통해 주어진 Saga에 대한 가시성을 확보할 수 있고 롤백이 용이하다.
- Saga        참가자는 다는 참가자의 명령에 대해 알 필요가 없으며 우려 사항의 명확한 분리는 비즈니스 논리를 간소화 한다.

단점 

- 관리를 해야 하는 Orchestration 서비스가 따로 추가되어야 하는 경우에는 인프라 구현이 복잡해 질 수 있다.
- 중앙집중적 형태이므로 도메인 커플링이        높아진다.
- 모든 트랜잭션을 Orchestration  Manager가 관리하기 때문에 로직이 복잡해진다.

![img](file:///C:/Users/kirobo/AppData/Local/Temp/msohtmlclip1/01/clip_image006.png)

​               Orchestration Saga 성공 시나리오

 

![img](file:///C:/Users/kirobo/AppData/Local/Temp/msohtmlclip1/01/clip_image008.png)

​             Orchestration Saga 실패시 보상 시나리오

**일반적으로 MSA 아키텍처 구성에서 데이터 흐름의 일관성 ,거래추적 ,가시성 확보 및 롤백이 용이한 Orchestration based SAGA 를 선택한다.**

 

 

## Orchestration 구현 아키텍처 

### API 기반의 Orchestration Saga 구성 (Sync)

·     

- API기반의 Saga구성은 일반적으로 MSA환경에서 많이 활용하고 있다.
- Saga      Manager는 동기 방식으로 API기반으로 요청 및 처리 결과를 받을 수 있으며, Saga 참여자들이 미처 처리하지 못한 통신 장애 및      timeout에 대한 에러 처리가 용이하다.

o  API기반의 Saga구성은 RestTemplate, WebClient, OpenFeign등 다양한 Rest API 활용이 가능하다.

장점

- 기능 구현이 쉬워 러닝 커브가 낮다.
-  RestAPI       다양한 툴을 사용 가능하다.
- 동기 기반의 트랜잭션 처리 흐름제어, 에러 및 장애시 처리가 용이 하다.
- 요청 후 timeout에 대한 에러 처리가 용이하다.

단점

- 이벤트 기반의 처리가 어렵다.

![img](file:///C:/Users/kirobo/AppData/Local/Temp/msohtmlclip1/01/clip_image009.png)

​               API 기반 Saga 시나리오

 

 

### Message Borker 기반의 Orchestration Saga 구성 (ASync)

·     

- Orchestration      기반의 Saga 처리시 Transaction 를      이벤트 기반으로 메시지 큐를 활용하는 아키텍처이다.
- Saga      참여자(App) 의 Transaction에      필요한 채널을 kafka topic으로 생성한다.
- Topic은 Transaction 기능별로 생성 가능하다. (예: orderCreate-topic, orderUpdate-topic)
- Saga      참여자(App)들은 보상 트랜잭션을 위해서      Saga 보상처리 topic을 같이 생성한다.      ( 예: orderCancel-topic)
- Saga      처리 결과에 (성공/실패) 는 Saga Reply Topic에 전송하여 SagaManager에게 통보한다.

o  SagaManager는 Saga Reply Topic 결과 값을 확인하여 다음 Saga처리 수행 또는 보상 처리 결정하여 수행한다.

장점 

- Eventual       Transaction기반의 비동기 Saga 처리로 Saga Transacton 메시지 Kafka Broker에       저장되어 전송 메시지 손실이 적다. 
- Saga       참여자(App)간의 높은 느슨한 결합이 가능하여 기능 구현이 용이하다.
- Transaction을       병렬로 처리해야 하는 비즈니스 업무에도 구현 및 확장 (Scability)도 용이하다.
- 전송한 Transaction 메시지는 kafka 브로커에 저장되므로       혹시 Saga참여자의 장애로 인한 메시지를 받지 못해도 추후 정상적으로 복구가 된다면 정상적으로       메시지를 받을 수 있다.
- 처리결과에 대한 결과를 단일 또는       복수의 채널(topic)으로 설정 가능하다.

단점 

- Saga       Manager 참여자들에게 Transaction 요청 후 Reply Topic으로부터 Consumer로 메시지를       받게 되어 있다. 만약 Saga참여자가 리턴을       주지 않는 경우(장애 발생시)에는 현재 Saga 진행을 알 수 없게 된다.
- Saga       Manager  Reply 메시지를 받지 않으며 다음 단계의 Saga 진행       사항을 할 수 없는 상황에 빠지게 되어 진행 중인 Saga대한 즉각적인 대응이 어렵다. 
- Saga       Manager 비동기로 진행하기 때문에 요청 후 처리 결과 수신은 독립된       Thread에서 실행된다. 따라서 일정 시간 동안 응답이 없는 경우 이를 관리할       프로세스를 따로 구현해야 하나 용이하지 않다.
- Saga       Transaction의 거래 처리에 대한 즉각적인 대응 서비스가       필요한 업무에는 적용 시 고려해야 한다.

![img](file:///C:/Users/kirobo/AppData/Local/Temp/msohtmlclip1/01/clip_image011.png)

​              Message Borker 기반 Saga 성공 시나리오

 

 

 

![img](file:///C:/Users/kirobo/AppData/Local/Temp/msohtmlclip1/01/clip_image013.png)

​           Message Borker 기반 Saga 실패시 보상 시나리오

 

 

## 샘플 프로젝트 및 DB 구성

### 프로젝트 구성

#### MS1(samp.ppon) 프로젝트

일반전화 계약 정보 처리와 관련된 API 

GitLab - https://gitlab.dspace.kt.co.kr/sa-guide/sample-projects/sample-api.git

#### MS2(samp.inetaplca) 프로젝트

인터넷응용 계약 정보 처리와 관련된 API 

GitLab - https://gitlab.dspace.kt.co.kr/icis-tr/sa/icis-samp-inetaplca.git

#### MS3(samp.intelnet) 프로젝트

지능망 계약 정보 처리와 관련된 API 

GibLab - https://gitlab.dspace.kt.co.kr/icis-tr/sa/icis-samp-intelnet.git

 

 

### DB TABLE 구성

#### SA_PPON_CONT

·     

o   

- 일반전화 계약 정보 테이블.
- MS1(samp.ppon)에서 Query & Command(C/U/D) 처리한다.

| **컬럼명**               | **데이터  유형** | **길이** | **설명**                                                  |
| ------------------------ | ---------------- | -------- | --------------------------------------------------------- |
| SA_PPON_CONT_NO          | NUMBER           |          | 일반전화 계약번호                                         |
| SA_INETAPLCA_CONT_NO     | NUMBER           |          | 인터넷응용 계약번호                                       |
| SA_INTELNET_CONT_NO      | NUMBER           |          | 지능망 계약번호                                           |
| CONTR                    | VARCHAR          | 200      | 계약자                                                    |
| PAYR                     | VARCHAR          | 200      | 납입자                                                    |
| SVC_NO                   | VARCHAR          | 200      | 서비스번호                                                |
| CARD_NO                  | VARCHAR          | 200      | 카드번호                                                  |
| BCBN_CONN                | VARCHAR          | 200      | 백본접속                                                  |
| RACK                     | VARCHAR          | 200      | 랙                                                        |
| INTL_TRAFC               | VARCHAR          | 200      | 국제트래픽                                                |
| MFAMT_CONT               | VARCHAR          | 200      | 월정액계약                                                |
| ADMR_INFO                | VARCHAR          | 200      | 관리자정보                                                |
| APYR                     | VARCHAR          | 200      | 신청자                                                    |
| SALER                    | VARCHAR          | 200      | 판매자                                                    |
| MKER_ID                  | VARCHAR          | 50       | 작성자ID                                                  |
| MKER_NM                  | VARCHAR          | 100      | 작성자명                                                  |
| MKE_DATE                 | TIMESTAMP(6)     | 12       | 작성일자                                                  |
| AMDR_ID                  | VARCHAR          | 50       | 수정자ID                                                  |
| AMDR_NM                  | VARCHAR          | 100      | 수정자명                                                  |
| AMD_DATE                 | TIMESTAMP(6)     | 12       | 수정일자                                                  |
| DEL_YN                   | VARCHAR          | 1        | 삭제여부  (N: 정상, Y: 삭제)                              |
| TRANSAC_NO               | VARCHAR          | 100      | 트랜잭션 번호  (통합UI  : globalNo)                       |
| TRANSAC_STTUS            | VARCHAR          | 100      | 트랜잭션 상태  (START:진행중, END:완료, CANCEL:취소/보상) |
| PREV_SA_PPON_CONT_HST_NO | NUMBER           |          | 이전 일반전화 계약 이력번호  (보상 트랜잭션 호출시 사용)  |

 

#### SA_PPON_CONT_HST

·     

o   

- 일반전화 계약 이력 정보 테이블.
- MS1(samp.ppon)에서       보상 처리시 사용하기 위한 이력을 저장한다.

| **컬럼명**               | **데이터  유형** | **길이** | **설명**                                                  |
| ------------------------ | ---------------- | -------- | --------------------------------------------------------- |
| SA_PPON_CONT_HST_NO      | NUMBER           |          | 일반전화 계약 이력번호                                    |
| SA_PPON_CONT_NO          | NUMBER           |          | 일반전화 계약번호                                         |
| SA_INETAPLCA_CONT_NO     | NUMBER           |          | 인터넷응용 계약번호                                       |
| SA_INTELNET_CONT_NO      | NUMBER           |          | 지능망 계약번호                                           |
| CONTR                    | VARCHAR          | 200      | 계약자                                                    |
| PAYR                     | VARCHAR          | 200      | 납입자                                                    |
| SVC_NO                   | VARCHAR          | 200      | 서비스번호                                                |
| CARD_NO                  | VARCHAR          | 200      | 카드번호                                                  |
| BCBN_CONN                | VARCHAR          | 200      | 백본접속                                                  |
| RACK                     | VARCHAR          | 200      | 랙                                                        |
| INTL_TRAFC               | VARCHAR          | 200      | 국제트래픽                                                |
| MFAMT_CONT               | VARCHAR          | 200      | 월정액계약                                                |
| ADMR_INFO                | VARCHAR          | 200      | 관리자정보                                                |
| APYR                     | VARCHAR          | 200      | 신청자                                                    |
| SALER                    | VARCHAR          | 200      | 판매자                                                    |
| MKER_ID                  | VARCHAR          | 50       | 작성자ID                                                  |
| MKER_NM                  | VARCHAR          | 100      | 작성자명                                                  |
| MKE_DATE                 | TIMESTAMP(6)     | 12       | 작성일자                                                  |
| AMDR_ID                  | VARCHAR          | 50       | 수정자ID                                                  |
| AMDR_NM                  | VARCHAR          | 100      | 수정자명                                                  |
| AMD_DATE                 | TIMESTAMP(6)     | 12       | 수정일자                                                  |
| DEL_YN                   | VARCHAR          | 1        | 삭제여부  (N: 정상, Y: 삭제)                              |
| TRANSAC_NO               | VARCHAR          | 100      | 트랜잭션 번호  (통합UI  : globalNo)                       |
| TRANSAC_STTUS            | VARCHAR          | 100      | 트랜잭션 상태  (START:진행중, END:완료, CANCEL:취소/보상) |
| PREV_SA_PPON_CONT_HST_NO | NUMBER           |          | 이전 일반전화 계약 이력번호  (보상 트랜잭션 호출시 사용)  |

 

#### SA_INETAPLCA_CONT

·     

o   

- 인터넷응용 계약 정보 테이블.
- MS2(samp.inetaplca)에서  Query & Command(C/U/D) 처리한다.

| **컬럼명**                    | **데이터  유형** | **길이** | **설명**                                                   |
| ----------------------------- | ---------------- | -------- | ---------------------------------------------------------- |
| SA_INETAPLCA_CONT_NO          | NUMBER           |          | 인터넷응용 계약번호                                        |
| SA_PPON_CONT_NO               | NUMBER           |          | 일반전화 계약번호                                          |
| CONTR                         | VARCHAR          | 200      | 계약자                                                     |
| PAYR                          | VARCHAR          | 200      | 납입자                                                     |
| SVC_NO                        | VARCHAR          | 200      | 서비스번호                                                 |
| CARD_NO                       | VARCHAR          | 200      | 카드번호                                                   |
| BCBN_CONN                     | VARCHAR          | 200      | 백본접속                                                   |
| RACK                          | VARCHAR          | 200      | 랙                                                         |
| INTL_TRAFC                    | VARCHAR          | 200      | 국제트래픽                                                 |
| MFAMT_CONT                    | VARCHAR          | 200      | 월정액계약                                                 |
| ADMR_INFO                     | VARCHAR          | 200      | 관리자정보                                                 |
| APYR                          | VARCHAR          | 200      | 신청자                                                     |
| SALER                         | VARCHAR          | 200      | 판매자                                                     |
| MKER_ID                       | VARCHAR          | 50       | 작성자ID                                                   |
| MKER_NM                       | VARCHAR          | 100      | 작성자명                                                   |
| MKE_DATE                      | TIMESTAMP(6)     | 12       | 작성일자                                                   |
| AMDR_ID                       | VARCHAR          | 50       | 수정자ID                                                   |
| AMDR_NM                       | VARCHAR          | 100      | 수정자명                                                   |
| AMD_DATE                      | TIMESTAMP(6)     | 12       | 수정일자                                                   |
| DEL_YN                        | VARCHAR          | 1        | 삭제여부  (N: 정상, Y: 삭제)                               |
| TRANSAC_NO                    | VARCHAR          | 100      | 트랜잭션 번호  (통합UI  : globalNo)                        |
| TRANSAC_STTUS                 | VARCHAR          | 100      | 트랜잭션 상태  (START:진행중, END:완료, CANCEL:취소/보상)  |
| PREV_SA_INETAPLCA_CONT_HST_NO | NUMBER           |          | 이전 인터넷응용 계약 이력번호  (보상 트랜잭션 호출시 사용) |

 

#### SA_INETAPLCA_CONT_HST

·     

o   

- 인터넷응용 계약 이력 정보 테이블.
- MS2(samp.inetaplca)에서       보상 처리시 사용하기 위한 이력을 저장한다.

| **컬럼명**                    | **데이터  유형** | **길이** | **설명**                                                   |
| ----------------------------- | ---------------- | -------- | ---------------------------------------------------------- |
| SA_INETAPLCA_CONT_HST_NO      | NUMBER           |          | 인터넷응용 계약 이력번호                                   |
| SA_INETAPLCA_CONT_NO          | NUMBER           |          | 인터넷응용 계약번호                                        |
| SA_PPON_CONT_NO               | NUMBER           |          | 일반전화 계약번호                                          |
| CONTR                         | VARCHAR          | 200      | 계약자                                                     |
| PAYR                          | VARCHAR          | 200      | 납입자                                                     |
| SVC_NO                        | VARCHAR          | 200      | 서비스번호                                                 |
| CARD_NO                       | VARCHAR          | 200      | 카드번호                                                   |
| BCBN_CONN                     | VARCHAR          | 200      | 백본접속                                                   |
| RACK                          | VARCHAR          | 200      | 랙                                                         |
| INTL_TRAFC                    | VARCHAR          | 200      | 국제트래픽                                                 |
| MFAMT_CONT                    | VARCHAR          | 200      | 월정액계약                                                 |
| ADMR_INFO                     | VARCHAR          | 200      | 관리자정보                                                 |
| APYR                          | VARCHAR          | 200      | 신청자                                                     |
| SALER                         | VARCHAR          | 200      | 판매자                                                     |
| MKER_ID                       | VARCHAR          | 50       | 작성자ID                                                   |
| MKER_NM                       | VARCHAR          | 100      | 작성자명                                                   |
| MKE_DATE                      | TIMESTAMP(6)     | 12       | 작성일자                                                   |
| AMDR_ID                       | VARCHAR          | 50       | 수정자ID                                                   |
| AMDR_NM                       | VARCHAR          | 100      | 수정자명                                                   |
| AMD_DATE                      | TIMESTAMP(6)     | 12       | 수정일자                                                   |
| DEL_YN                        | VARCHAR          | 1        | 삭제여부  (N: 정상, Y: 삭제)                               |
| TRANSAC_NO                    | VARCHAR          | 100      | 트랜잭션 번호  (통합UI  : globalNo)                        |
| TRANSAC_STTUS                 | VARCHAR          | 100      | 트랜잭션 상태  (START:진행중, END:완료, CANCEL:취소/보상)  |
| PREV_SA_INETAPLCA_CONT_HST_NO | NUMBER           |          | 이전 인터넷응용 계약 이력번호  (보상 트랜잭션 호출시 사용) |

 

#### SA_INTELNET_CONT

·     

o   

- 지능망 계약 정보 테이블.
- MS3(samp.intelnet)에서 Query & Command(C/U/D) 처리한다.

| **컬럼명**                   | **데이터  유형** | **길이** | **설명**                                                  |
| ---------------------------- | ---------------- | -------- | --------------------------------------------------------- |
| SA_INTELNET_CONT_NO          | NUMBER           |          | 지능망 계약번호                                           |
| SA_PPON_CONT_NO              | NUMBER           |          | 일반전화 계약번호                                         |
| CONTR                        | VARCHAR          | 200      | 계약자                                                    |
| PAYR                         | VARCHAR          | 200      | 납입자                                                    |
| SVC_NO                       | VARCHAR          | 200      | 서비스번호                                                |
| CARD_NO                      | VARCHAR          | 200      | 카드번호                                                  |
| BCBN_CONN                    | VARCHAR          | 200      | 백본접속                                                  |
| RACK                         | VARCHAR          | 200      | 랙                                                        |
| INTL_TRAFC                   | VARCHAR          | 200      | 국제트래픽                                                |
| MFAMT_CONT                   | VARCHAR          | 200      | 월정액계약                                                |
| ADMR_INFO                    | VARCHAR          | 200      | 관리자정보                                                |
| APYR                         | VARCHAR          | 200      | 신청자                                                    |
| SALER                        | VARCHAR          | 200      | 판매자                                                    |
| MKER_ID                      | VARCHAR          | 50       | 작성자ID                                                  |
| MKER_NM                      | VARCHAR          | 100      | 작성자명                                                  |
| MKE_DATE                     | TIMESTAMP(6)     | 12       | 작성일자                                                  |
| AMDR_ID                      | VARCHAR          | 50       | 수정자ID                                                  |
| AMDR_NM                      | VARCHAR          | 100      | 수정자명                                                  |
| AMD_DATE                     | TIMESTAMP(6)     | 12       | 수정일자                                                  |
| DEL_YN                       | VARCHAR          | 1        | 삭제여부  (N: 정상, Y: 삭제)                              |
| TRANSAC_NO                   | VARCHAR          | 100      | 트랜잭션 번호  (통합UI  : globalNo)                       |
| TRANSAC_STTUS                | VARCHAR          | 100      | 트랜잭션 상태  (START:진행중, END:완료, CANCEL:취소/보상) |
| PREV_SA_INTELNET_CONT_HST_NO | NUMBER           |          | 이전 지능망 계약 이력번호  (보상 트랜잭션 호출시 사용)    |

 

#### SA_INTELNET_CONT_HST

·     

o   

- 지능망 계약 이력 정보 테이블.
- MS3(samp.intelnet)에서       보상 처리시 사용하기 위한 이력을 저장한다.

| **컬럼명**                   | **데이터  유형** | **길이** | **설명**                                                  |
| ---------------------------- | ---------------- | -------- | --------------------------------------------------------- |
| SA_INTELNET_CONT_HST_NO      | NUMBER           |          | 지능망 계약 이력번호                                      |
| SA_INTELNET_CONT_NO          | NUMBER           |          | 지능망 계약번호                                           |
| SA_PPON_CONT_NO              | NUMBER           |          | 일반전화 계약번호                                         |
| CONTR                        | VARCHAR          | 200      | 계약자                                                    |
| PAYR                         | VARCHAR          | 200      | 납입자                                                    |
| SVC_NO                       | VARCHAR          | 200      | 서비스번호                                                |
| CARD_NO                      | VARCHAR          | 200      | 카드번호                                                  |
| BCBN_CONN                    | VARCHAR          | 200      | 백본접속                                                  |
| RACK                         | VARCHAR          | 200      | 랙                                                        |
| INTL_TRAFC                   | VARCHAR          | 200      | 국제트래픽                                                |
| MFAMT_CONT                   | VARCHAR          | 200      | 월정액계약                                                |
| ADMR_INFO                    | VARCHAR          | 200      | 관리자정보                                                |
| APYR                         | VARCHAR          | 200      | 신청자                                                    |
| SALER                        | VARCHAR          | 200      | 판매자                                                    |
| MKER_ID                      | VARCHAR          | 50       | 작성자ID                                                  |
| MKER_NM                      | VARCHAR          | 100      | 작성자명                                                  |
| MKE_DATE                     | TIMESTAMP(6)     | 12       | 작성일자                                                  |
| AMDR_ID                      | VARCHAR          | 50       | 수정자ID                                                  |
| AMDR_NM                      | VARCHAR          | 100      | 수정자명                                                  |
| AMD_DATE                     | TIMESTAMP(6)     | 12       | 수정일자                                                  |
| DEL_YN                       | VARCHAR          | 1        | 삭제여부  (N: 정상, Y: 삭제)                              |
| TRANSAC_NO                   | VARCHAR          | 100      | 트랜잭션 번호  (통합UI  : globalNo)                       |
| TRANSAC_STTUS                | VARCHAR          | 100      | 트랜잭션 상태  (START:진행중, END:완료, CANCEL:취소/보상) |
| PREV_SA_INTELNET_CONT_HST_NO | NUMBER           |          | 이전 지능망 계약 이력번호  (보상 트랜잭션 호출시 사용)    |

 

## 샘플 시나리오 - Orchestration based SAGA

### API 구현 방식

·     

o  API 연동 Framework는 OpenFeign을 사용한다.

OpenFeign 가이드 페이지 - [OpenFeign 가이드]()

#### API 기반 SAGA 시나리오 (Ochestration)

##### 등록 시나리오 (성공)

![img](file:///C:/Users/kirobo/AppData/Local/Temp/msohtmlclip1/01/clip_image015.png)

1. 요청

 1-1. (통합UI) 계약 등록 요청

  \- KOS-B2C 구동 후 "샘플PA3 > ICIS-TR 1단계 샘플 > SAGA - api방식" 선택

  \- "행추가" 버튼 클릭 후, 그리드에 추가된 신규행에 값 입력

  \- 값 입력 후 "**등록**" 버튼 클릭하여 입력값 저장

​    ![img](file:///C:/Users/kirobo/AppData/Local/Temp/msohtmlclip1/01/clip_image017.png)

​    

 

 1-2. (MS1) 일반전화 계약 등록

  \- **INSERT INTO SA_PPON_CONT**

  \- SA_PPON_CONT_NO(일반전화 계약번호) : SA_PPON_CONT_SEQ.NEXTVAL

  \- DEL_YN(삭제유무) : 'N'

  \- TRANSAC_NO(트랜잭션 번호) : 통합UI globalNo

  \- TRANSAC_STTUS(트랜잭션 상태) : **'START'**

![img](file:///C:/Users/kirobo/AppData/Local/Temp/msohtmlclip1/01/clip_image019.png)

 

2. MS2 트랜잭션 요청

 2-1. (MS1) 인터넷응용 계약 등록 API 호출

 2-2. (MS2) 인터넷응용 계약 등록

  \- **INSERT INTO** **SA_INETAPLCA_CONT**

  \- SA_INETAPLCA_CONT_NO(인터넷응용 계약번호) : SA_INETAPLCA_CONT_SEQ.NEXTVAL

  \- DEL_YN(삭제유무) : 'N'

  \- TRANSAC_NO(트랜잭션 번호) : 통합UI globalNo

  \- TRANSAC_STTUS(트랜잭션 상태) : **'END'**

![img](file:///C:/Users/kirobo/AppData/Local/Temp/msohtmlclip1/01/clip_image021.png)

 

3. MS2 트랜잭션 완료

 3-1. (MS2) 트랜잭션 정상처리 응답

 3-2. (MS1) 인터넷응용 계약번호 UPDATE

  \- **UPDATE** **SA_PPON_CONT**

  \- SA_INETAPLCA_CONT_NO(인터넷응용 계약번호) : 트랜잭션 응답받은 인터넷응용 계약번호

![img](file:///C:/Users/kirobo/AppData/Local/Temp/msohtmlclip1/01/clip_image023.png)

 

4. MS3 트랜잭션 요청

 4-1. (MS1) 지능망 계약 등록 API 호출

 4-2. (MS3) 지능망 계약 등록

  \- **INSERT INTO** **SA_INTELNET_CONT**

  \- SA_INTELNET_CONT_NO(지능망 계약번호) : SA_INTELNET_CONT_SEQ.NEXTVAL

  \- DEL_YN(삭제유무) : 'N'

  \- TRANSAC_NO(트랜잭션 번호) : 통합UI globalNo

  \- TRANSAC_STTUS(트랜잭션 상태) : **'END'**

![img](file:///C:/Users/kirobo/AppData/Local/Temp/msohtmlclip1/01/clip_image025.png)

 

5. MS3 트랜잭션 완료

 5-1. (MS3) 트랜잭션 정상처리 응답

 5-2. (MS1) 지능망 계약번호 UPDATE

  \- **UPDATE** **SA_PPON_CONT**

  \- SA_INTELNET_CONT_NO(지능망 계약번호) : 트랜잭션 응답받은 지능망 계약번호

![img](file:///C:/Users/kirobo/AppData/Local/Temp/msohtmlclip1/01/clip_image027.png)

 

 5-3 . (MS1) 트랜잭션 상태 UPDATE

  \- **UPDATE** **SA_PPON_CONT**

  \- TRANSAC_STTUS(트랜잭션 상태) : **'END'**

![img](file:///C:/Users/kirobo/AppData/Local/Temp/msohtmlclip1/01/clip_image029.png)

 

6. 응답

 6-1. (MS1) 계약 등록 정상 응답

![img](file:///C:/Users/kirobo/AppData/Local/Temp/msohtmlclip1/01/clip_image031.png)

 

##### 등록 시나리오 (보상)

![img](file:///C:/Users/kirobo/AppData/Local/Temp/msohtmlclip1/01/clip_image033.png)

1. 요청

 1-1. (통합UI) 계약 등록 요청

  \- KOS-B2C 구동 후 "샘플PA3 > ICIS-TR 1단계 샘플 > SAGA - api방식" 선택

  \- "행추가" 버튼 클릭 후, 그리드에 추가된 신규행에 값 입력

  \- 값 입력 후 "**등록**" 버튼 클릭하여 입력값 저장

 

 1-2. (MS1) 일반전화 계약 등록

  \- **INSERT INTO SA_PPON_CONT**

  \- SA_PPON_CONT_NO(일반전화 계약번호) : SA_PPON_CONT_SEQ.NEXTVAL

  \- DEL_YN(삭제유무) : 'N'

  \- TRANSAC_NO(트랜잭션 번호) : 통합UI globalNo

  \- TRANSAC_STTUS(트랜잭션 상태) : **'START'**

![img](file:///C:/Users/kirobo/AppData/Local/Temp/msohtmlclip1/01/clip_image035.png)

 

2. MS2 트랜잭션 요청

 2-1. (MS1) 인터넷응용 계약 등록 API 호출

 2-2. (MS2) 인터넷응용 계약 등록

  \- **INSERT INTO** **SA_INETAPLCA_CONT**

  \- SA_INETAPLCA_CONT_NO(인터넷응용 계약번호) : SA_INETAPLCA_CONT_SEQ.NEXTVAL

  \- DEL_YN(삭제유무) : 'N'

  \- TRANSAC_NO(트랜잭션 번호) : 통합UI globalNo

  \- TRANSAC_STTUS(트랜잭션 상태) : **'END'**

![img](file:///C:/Users/kirobo/AppData/Local/Temp/msohtmlclip1/01/clip_image037.png)

 

3. MS2 트랜잭션 완료

 3-1. (MS2) 트랜잭션 정상처리 응답

 3-2. (MS1) 인터넷응용 계약번호 UPDATE

  \- **UPDATE** **SA_PPON_CONT**

  \- SA_INETAPLCA_CONT_NO(인터넷응용 계약번호) : 트랜잭션 응답받은 인터넷응용 계약번호

![img](file:///C:/Users/kirobo/AppData/Local/Temp/msohtmlclip1/01/clip_image039.png)

 

4. MS3 트랜잭션 요청

 4-1. (MS1) 지능망 계약 등록 API 호출

 4-2. (MS3) 지능망 계약 등록 중 **EXCEPTION** **발생**

 

5. **MS3** **트랜잭션 실패**

 5-1. (MS3) 트랜잭션 실패처리 응답

 

6. **MS2** **보상 트랜잭션 요청**

 6-1. (MS1) 인터넷응용 계약 등록 보상처리 API 호출

 6-2. **(MS2)** **인터넷응용 계약 등록 보상처리**

  \- **UPDATE** **SA_INETAPLCA_CONT**

  \- DEL_YN(삭제유무) : **'Y'**

![img](file:///C:/Users/kirobo/AppData/Local/Temp/msohtmlclip1/01/clip_image041.png)

 

7. MS2 보상 트랜잭션 완료

 7-1. (MS2) 보상 트랜잭션 정상처리 응답

 7-2. **(MS1)** **일반전화 계약 등록 취소처리**

  \- **UPDATE** **SA_PPON_CONT**

  \- DEL_YN(삭제유무) : **'Y'**

![img](file:///C:/Users/kirobo/AppData/Local/Temp/msohtmlclip1/01/clip_image043.png)

 

8. 응답

 8-1. (MS1) 계약 등록 실패 응답

 

##### 수정 시나리오 (성공)

![img](file:///C:/Users/kirobo/AppData/Local/Temp/msohtmlclip1/01/clip_image045.png)

1. 요청

 1-1. (통합UI) 계약 등록 요청

  \- KOS-B2C 구동 후 "샘플PA3 > ICIS-TR 1단계 샘플 > SAGA - api방식" 선택

  \- 값 수정 후 "**수정**" 버튼 클릭하여 입력값 저장

​    

 1-2. (MS1) 일반전화 계약 이력 등록

  \- **INSERT INTO SA_PPON_CONT_HST**

  \- 취소 및 보상처리를 위한 **수정 전 원본 데이터 저장**

![img](file:///C:/Users/kirobo/AppData/Local/Temp/msohtmlclip1/01/clip_image047.png)

 

 1-3. (MS1) 일반전화 계약 수정

  \- **UPDATE SA_PPON_CONT**

  \- TRANSAC_STTUS(트랜잭션 상태) : **'START'**

  \- PREV_SA_PPON_CONT_HST_NO(이전 일반전화 계약 이력번호) : **1-2****에서 신규 생성된 시퀀스(SA_PPON_CONT_HST)**

![img](file:///C:/Users/kirobo/AppData/Local/Temp/msohtmlclip1/01/clip_image049.png)

 

2. MS2 트랜잭션 요청

 2-1. (MS1) 인터넷응용 계약 수정 API 호출

 

 2-2. (MS2) 인터넷응용 계약 이력 등록

  \- **INSERT INTO SA_INETAPLCA_CONT_HST**

  \- 취소 및 보상처리를 위한 **수정 전 원본 데이터 저장**

![img](file:///C:/Users/kirobo/AppData/Local/Temp/msohtmlclip1/01/clip_image051.png)

 

 2-3. (MS2) 인터넷응용 계약 수정

  \- **UPDATE**  **SA_INETAPLCA_CONT**

  \- TRANSAC_STTUS(트랜잭션 상태) : **'END'**

  \- PREV_SA_INETAPLCA_CONT_HST_NO(이전 인터넷응용 계약 이력번호) : **2-2****에서 신규 생성된 시퀀스(SA_PPON_CONT_HST)**

![img](file:///C:/Users/kirobo/AppData/Local/Temp/msohtmlclip1/01/clip_image053.png)

 

3. MS2 트랜잭션 완료

 3-1. (MS2) 트랜잭션 정상처리 응답

 3-2. (MS1) 트랜잭션 응답 완료

 

4. MS3 트랜잭션 요청

 4-1. (MS1) 지능망 계약 등록 API 호출

 

 4-2. (MS3) 지능망 계약 이력 등록

  \- **INSERT INTO SA_INTELNET_CONT_HST**

  \- 취소 및 보상처리를 위한 **수정 전 원본 데이터 저장**

![img](file:///C:/Users/kirobo/AppData/Local/Temp/msohtmlclip1/01/clip_image055.png)

 

 4-2. (MS3) 지능망 계약 수정

  \- **UPDATE** **SA_INTELNET_CONT**

  \- TRANSAC_STTUS(트랜잭션 상태) : **'END'**

  \- PREV_SA_INTELNET_CONT_HST_NO(이전 지능망 계약 이력번호) : **4-2****에서 신규 생성된 시퀀스(SA_PPON_CONT_HST)**

![img](file:///C:/Users/kirobo/AppData/Local/Temp/msohtmlclip1/01/clip_image057.png)

 

5. MS3 트랜잭션 완료

 5-1. (MS3) 트랜잭션 정상처리 응답

 

 5-2 . (MS1) 트랜잭션 상태 UPDATE

  \- **UPDATE** **SA_PPON_CONT**

  \- TRANSAC_STTUS(트랜잭션 상태) : **'END'**

![img](file:///C:/Users/kirobo/AppData/Local/Temp/msohtmlclip1/01/clip_image059.png)

 

6. 응답

 6-1. (MS1) 계약 등록 정상 응답

 

##### 수정 시나리오 (보상)

![img](file:///C:/Users/kirobo/AppData/Local/Temp/msohtmlclip1/01/clip_image061.png)

1. 요청

 1-1. (통합UI) 계약 등록 요청

  \- KOS-B2C 구동 후 "샘플PA3 > ICIS-TR 1단계 샘플 > SAGA - api방식" 선택

  \- 값 수정 후 "**수정**" 버튼 클릭하여 입력값 저장

 

 1-2. (MS1) 일반전화 계약 이력 등록

  \- **INSERT INTO SA_PPON_CONT_HST**

  \- 취소 및 보상처리를 위한 **수정 전 원본 데이터 저장**

![img](file:///C:/Users/kirobo/AppData/Local/Temp/msohtmlclip1/01/clip_image063.png)

 

 1-3. (MS1) 일반전화 계약 수정

  \- **UPDATE SA_PPON_CONT**

  \- TRANSAC_STTUS(트랜잭션 상태) : **'START'**

  \- PREV_SA_PPON_CONT_HST_NO(이전 일반전화 계약 이력번호) : **1-2****에서 신규 생성된 시퀀스(SA_PPON_CONT_HST)**

![img](file:///C:/Users/kirobo/AppData/Local/Temp/msohtmlclip1/01/clip_image065.png)

 

2. MS2 트랜잭션 요청

 2-1. (MS1) 인터넷응용 계약 수정 API 호출

 

 2-2. (MS2) 인터넷응용 계약 이력 등록

  \- **INSERT INTO SA_INETAPLCA_CONT_HST**

  \- 취소 및 보상처리를 위한 **수정 전 원본 데이터 저장**

![img](file:///C:/Users/kirobo/AppData/Local/Temp/msohtmlclip1/01/clip_image067.png)

 

 2-3. (MS2) 인터넷응용 계약 수정

  \- **UPDATE**  **SA_INETAPLCA_CONT**

  \- TRANSAC_STTUS(트랜잭션 상태) : **'END'**

  \- PREV_SA_INETAPLCA_CONT_HST_NO(이전 인터넷응용 계약 이력번호) : **2-2****에서 신규 생성된 시퀀스(SA_PPON_CONT_HST)**

![img](file:///C:/Users/kirobo/AppData/Local/Temp/msohtmlclip1/01/clip_image069.png)

 

3. MS2 트랜잭션 완료

 3-1. (MS2) 트랜잭션 정상처리 응답

 3-2. (MS1) 트랜잭션 응답 완료

 

4. MS3 트랜잭션 요청

 4-1. (MS1) 지능망 계약 수정 API 호출

 4-2. (MS3) 지능망 계약 수정 중 **EXCEPTION** **발생**

 

5. **MS3** **트랜잭션 실패**

 5-1. (MS3) 트랜잭션 실패처리 응답

 

6. **MS2** **보상 트랜잭션 요청**

 6-1. (MS1) 인터넷응용 계약 수정 보상처리 API 호출

 6-2. **(MS2)** **인터넷응용 계약 수정 보상처리**

  \- **UPDATE** **SA_INETAPLCA_CONT**

​    **SELECT** ...

​    FROM **SA_INETAPLCA_CONT_HST**

​    WHERE **SA_INETAPLCA_CONT_HST_NO** = ( **SA_INETAPLCA_CONT** 의 **PREV_SA_INETAPLCA_CONT_HST_NO** )

![img](file:///C:/Users/kirobo/AppData/Local/Temp/msohtmlclip1/01/clip_image071.png)

 

7. MS2 보상 트랜잭션 완료

 7-1. (MS2) 보상 트랜잭션 정상처리 응답

 7-2. **(MS1)** **일반전화 계약 수정 취소처리**

  \- **UPDATE** **SA_PPON_CONT**

​    **SELECT** ...

​    FROM **SA_PPON_CONT_HST** 

​    WHERE **SA_PPON_CONT_HST_NO** = ( **SA_PPON_CONT** 의 **PREV_SA_PPON_CONT_HST_NO** )

![img](file:///C:/Users/kirobo/AppData/Local/Temp/msohtmlclip1/01/clip_image073.png)

 

8. 응답

 8-1. (MS1) 계약 등록 실패 응답

 

 

#### MS1 (samp.ppon)

·     

o   

- Orchestraion       방식에서 MS1(samp.ppon) API는 오케스트레이터역할을 수행한다.

§ 프로젝트 구조는 다음과 같이 구성된다.

**Guide Project Structure** 

```
D:.
├─.mvn
├─.settings
├─.vscode
├─bin
├─gitops
├─k8s
├─lib
├─logs
├─src
│  ├─main
│  │  ├─java
│  │  │  ├─com
│  │  │  │  └─kt
│  │  │  │      └─icis
│  │  │  │          ├─cmmn
│  │  │  │          ├─cmmnfrwk
│  │  │  │          └─samp
│  │  │  │              └─ppon
│  │  │  │                  ├─cont
│  │  │  │                  │  ├─command
│  │  │  │                  │  │  ├─endpoint        # Endpoint : 통합 UI 호출 메서드
│  │  │  │                  │  │  ├─payload
│  │  │  │                  │  │  ├─repository
│  │  │  │                  │  │  │  └─domain
│  │  │  │                  │  │  └─service
│  │  │  │                  │  └─query
│  │  │  │                  ├─exception
│  │  │  │                  │  └─endpoint
│  │  │  │                  ├─pubsub                        
│  │  │  │                  └─saga                    # SAGA Manager (API 기반)
│  │  │  │                      ├─client                  # SAGA API Feign Client 
│  │  │  │                      ├─payload                 # SAGA API payload 객체 
│  │  │  │                      └─service                  # SAGA 서비스 구현
│  │  │  └─org
│  │  └─resources
│  │      ├─config
│  │      └─META-INF
│  └─test
└─target
```

##### (Endpoint) CommandPponContEndpoint.java

·     

o   

§  

§ SAGA 요청을 처리하기 위한 EndPoint 작성한다. 

**CommandPponContEndpoint.java** 

```
//...
@RestController
@RequestMapping("/cont")
@RequiredArgsConstructor
@Tag(name = "일반전화 계약 Command", description = "일반전화 계약 - 등록/수정/삭제")
@Slf4j
public class CommandPponContEndpoint {
 
       // ...
    private final ContApiSagaManager contApiSagaManager;    // [SAGA-Api] 호출
 
       // ...
 
    /**
     * <pre>
     * [SAGA-Api] 일반전화 계약 등록
     * </pre>
     *
     * @param CommandPponContInDs commandPponContDs
     * @return CommandPponContOutDs
     */
    @Operation(summary = "[SAGA-Api] 일반전화 계약 등록", description = "일반전화 계약을 등록하는 기능입니다.")
    @PostMapping(path = "/regSagaApi")
    public CommandPponContOutDs regSagaApi(@RequestBody CommandPponContInDs commandPponContDs) {
        log.info("[ICISTR:{}] === [CommandPponContEndpoint.regSagaApi] === requestBody : {}",
                CommonUtil.getCommonHeader().getGlobalNo(), commandPponContDs);
 
        int result = contSagaManager.regSagaApi(commandPponContDs);
        return getResultMessage(result);
    }
 
 
       // ...
 
}
```

·     

o   

§  

§  

- Line 28 : API 기반 SAGA(계약 등록)을 위한 SagaManager의 등록 트랜잭션(regSagaApi)을 호출한다. 

 

 

 

##### (SagaManager) ContApiSagaManager.java

·     

o   

§  

§ SAGA 트랜잭션 관리를 위한 SagaManager를 작성한다.

**ContSagaManager.java** 

```
//...
@Component
@Slf4j
@RequiredArgsConstructor
public class ContSagaManager {
 
       // ...      
 
 
       /**
        * <pre>
        * [SAGA-Api] 계약 등록
        * </pre>
        *
        * @param CommandPponContInDs commandPponContDs
        * @return int
        */
       public int regSagaApi(CommandPponContInDs commandPponContDs) throws Exception {
 
              PponContDomain paramDomain = PponContDomain.of(commandPponContDs.getPponContPayload());
              int saPpontContNo = commandPponContRepository.nextVal();
              int saInetaplcaContNo = 0;
              int saIntelnetContNo = 0;
 
              // =============================================================================================================
              // [MS-1] 일반전화 계약 등록
              // =============================================================================================================
              pponContSagaService.regPponCont(paramDomain, saPpontContNo);
              log.info("[ICISTR:{}] === [ContSagaManager.regSagaApi] === [MS-1] 일반전화 계약 등록 === paramDomain : {}, saPpontContNo : {}",CommonUtil.getCommonHeader().getGlobalNo(), paramDomain, saPpontContNo);
 
              // =============================================================================================================
              // [MS-2 인터넷응용 계약 등록
              // =============================================================================================================
              log.info("[ICISTR:{}] === [ContSagaManager.regSagaApi] === [MS-2] 인터넷응용 계약 등록 API 호출 === paramDomain : {}, saPpontContNo : {}",CommonUtil.getCommonHeader().getGlobalNo(), paramDomain, saPpontContNo);
              try {
                      // [MS-2] 인터넷응용 계약 등록 API 호출
                     paramDomain.setSaPponContNo(saPpontContNo);
                      SagaInetaplcaContOutDs SagaInetaplcaContOutDs = inetaplcaContSagaService.regInetaplcaCont(paramDomain);
 
                      // === API 호출 성공 ===
                      if ("Y".equals(SagaInetaplcaContOutDs.getSagaInetaplcaContPayload().getSuccessYn())) {
 
                             // [MS-1] 일반전화 : 인터넷응용 계약번호 업데이트
                             saInetaplcaContNo = SagaInetaplcaContOutDs.getSagaInetaplcaContPayload().getSaInetaplcaContNo();
                             pponContSagaService.amdSaInetaplcaContNo(saPpontContNo, saInetaplcaContNo);
                             log.info("[ICISTR:{}] === [ContSagaManager.regSagaApi] === [MS-1]  일반전화 : 인터넷응용 계약번호 업데이트 === saPpontContNo : {}, saInetaplcaContNo : {}",CommonUtil.getCommonHeader().getGlobalNo(), saPpontContNo, saInetaplcaContNo);
 
                      }
                      // === API 호출 실패 ===
                      else {
                             // [MS-1] 일반전화 계약 등록 (취소처리)
                             regPponContCancel(saPpontContNo);
                             return FAIL;
 
                      }
 
              } catch (Exception e) {
                      // [MS-1] 일반전화 계약 등록 (취소처리)
                      regPponContCancel(saPpontContNo);
 
                      throw e;
              }
 
              // =============================================================================================================
              // [MS-3] 지능망 계약 등록
              // =============================================================================================================
              log.info("[ICISTR:{}] === [ContSagaManager.regSagaApi] === [MS-3] 지능망 계약 등록 API 호출 === paramDomain : {}, saPpontContNo : {}",CommonUtil.getCommonHeader().getGlobalNo(), paramDomain, saPpontContNo);
              try {
                      // [MS-3] 지능망 계약 등록 API 호출
                     paramDomain.setSaPponContNo(saPpontContNo);
                     paramDomain.setSaInetaplcaContNo(saInetaplcaContNo);
                      SagaIntelnetContOutDs sagaIntelnetContOutDs = intelnetContSagaService.regIntelnetCont(paramDomain);
 
                      // === API 호출 성공 ===
                      if ("Y".equals(sagaIntelnetContOutDs.getSagaIntelnetContPayload().getSuccessYn())) {
                             // [MS-1] 일반전화 : 지능망 계약번호 업데이트
                             saIntelnetContNo = sagaIntelnetContOutDs.getSagaIntelnetContPayload().getSaIntelnetContNo();
                             pponContSagaService.amdSaIntelnetContNo(saPpontContNo, saIntelnetContNo);
                             log.info("[ICISTR:{}] === [ContSagaManager.regSagaApi] === [MS-1]  일반전화 : 지능망 계약번호 업데이트 === saPpontContNo : {}, saIntelnetContNo : {}",CommonUtil.getCommonHeader().getGlobalNo(), saPpontContNo, saIntelnetContNo);
                      }
                      // === API 호출 실패 ===
                      else {
                             // === FALLBACK ===
                             if ("FALLBACK".equals(sagaIntelnetContOutDs.getSagaIntelnetContPayload().getSuccessYn())) {
                                     log.info("[ICISTR:{}] === [ContSagaManager.regSagaApi] === [MS-3] 지능망 계약 등록 API 호출 FALLBACK === message : {}",CommonUtil.getCommonHeader().getGlobalNo(),sagaIntelnetContOutDs.getSagaIntelnetContPayload().getMessage());
 
                                     // [MS-2] 인터넷응용 계약 등록 (보상처리)
                                    regInetaplcaContCompensating(saInetaplcaContNo);
 
                                     // [MS-1] 일반전화 계약 등록 (취소처리)
                                    regPponContCancel(saPpontContNo);
 
                                     return FAIL;
                             }
                             // === 그 외 실패 ===
                             else {
 
                                     // [MS-2] 인터넷응용 계약 등록 (보상처리)
                                    regInetaplcaContCompensating(saInetaplcaContNo);
 
                                     // [MS-1] 일반전화 계약 등록 (취소처리)
                                    regPponContCancel(saPpontContNo);
 
                                     return FAIL;
 
                             }
                      }
 
              } catch (Exception e) {
                      // [MS-2] 인터넷응용 계약 등록 (보상처리)
                     regInetaplcaContCompensating(saInetaplcaContNo);
 
                      // [MS-1] 일반전화 계약 등록 (취소처리)
                      regPponContCancel(saPpontContNo);
 
                      throw e;
              }
 
              // =============================================================================================================
              // [MS-1] 일반전화 : 트랜잭션 처리상태 업데이트 (END)
              // =============================================================================================================
              amdTransacSttus(saPpontContNo);
 
              log.info("[ICISTR] === [PponContSagaManager.regSagaApi] === SAGA-regSagaApi 성공!!");
 
              return SUCCESS;
       }
 
       /**
        * <pre>
        * [SAGA-Api] [MS-1] 일반전화 : 트랜잭션 처리상태 업데이트 (END)
        * </pre>
        *
        * @param int saPpontContNo
        * @return void
        */
       private void amdTransacSttus(int saPpontContNo) {
              pponContSagaService.amdTransacSttus(saPpontContNo);
              log.info("[ICISTR:{}] === [ContSagaManager.regSagaApi] === [MS-1]  일반전화 : 트랜잭션 처리상태 업데이트 (END) === saPpontContNo : {}",CommonUtil.getCommonHeader().getGlobalNo(), saPpontContNo);
       }
 
       /**
        * <pre>
        * [SAGA-Api] [MS-2] 인터넷응용 계약 등록 (보상처리)
        * </pre>
        *
        * @param int saInetaplcaContNo
        * @return void
        */
       public void regInetaplcaContCompensating(int saInetaplcaContNo) {
        inetaplcaContSagaService.regInetaplcaContCompensating(saInetaplcaContNo);
              log.info("[ICISTR:{}] === [ContSagaManager.regSagaApi] === [MS-2] 보상처리 : 인터넷응용 계약 등록 === saInetaplcaContNo : {}",CommonUtil.getCommonHeader().getGlobalNo(), saInetaplcaContNo);
       }
 
       /**
        * <pre>
        * [SAGA-Api] [MS-1] 일반전화 계약 등록 (취소처리)
        * </pre>
        *
        * @param int saPpontContNo
        * @return void
        */
       public void regPponContCancel(int saPpontContNo) {
              pponContSagaService.regPponContCancel(saPpontContNo);
              log.info("[ICISTR:{}] === [ContSagaManager.regSagaApi] === [MS-1] 일반전화 계약 등록 (취소처리) === saPpontContNo : {}",CommonUtil.getCommonHeader().getGlobalNo(), saPpontContNo);
       }
 
 
       // ...
 
}
```

###### 계약 등록 시나리오 (성공)

Line 28 : 일반전화(MS1) 계약 등록 서비스 호출

​        (PponContSagaService.java 등록 비즈니스 로직 수행)

​        \>> **(MS1)** **등록 완료 (TRANSAC_STTUS = 'START')**

Line 38 : 인터넷응용(MS2) 계약 등록 서비스 호출 

​       (InetaplcaContSagaService.java 등록 트랜잭션 요청)

​        \>> **(MS2)** **트랜잭션 완료**

Line 44~45 : 인터넷응용(MS2) 트랜잭션 완료 후, 인터넷응용 계약번호 UPDATE

Line 72 : 지능망(MS3) 계약 등록 서비스 호출

​       (IntelnetContSagaService.java 등록 트랜잭션 요청)

​        \>> **(MS3)** **트랜잭션 완료**

Line 77~78 : 지능망(MS3) 트랜잭션 완료 후, 지능망 계약번호 UPDATE

Line 122 : 트랜잭션 처리상태 UPDATE

​        \>> **(MS1)** **등록 완료 (TRANSAC_STTUS = 'END')** 

 

###### 계약 등록 시나리오 (보상)

Line 28 : 일반전화(MS1) 계약 등록 서비스 호출 

​        (PponContSagaService.java 등록 비즈니스 로직 수행)

​        \>> **(MS1)** **등록 완료 (TRANSAC_STTUS = 'START')**

Line 38 : 인터넷응용(MS2) 계약 등록 서비스 호출 

​       (InetaplcaContSagaService.java 등록 트랜잭션 요청)

​        \>> **(MS2)** **트랜잭션 완료**

Line 44~45 : 인터넷응용(MS2) 트랜잭션 완료 후, 인터넷응용 계약번호 UPDATE

Line 72 : 지능망(MS3) 계약 등록 서비스 호출 

​       (IntelnetContSagaService.java 등록 트랜잭션 요청)

​        \>> **(MS3)** **트랜잭션 실패 (ex:Feign FallBack)**

Line 88 : 인터넷응용(MS2) 계약 등록의 보상처리 서비스 호출 

​       (InetaplcaContSagaService.java 등록 보상 트랜잭션 요청)

​       \>> **(MS2)** **보상 트랜잭션 완료 (DEL_YN = 'Y', TRANSAC_STTUS = 'CANCEL')**

Line 91 : 일반전화(MS1) 계약 등록의 취소처리 서비스 호출 

​       (PponContSagaService.java 등록 취소처리 비즈니스 로직 수행)

​       \>> **(MS1)** **취소 완료 (DEL_YN = 'Y', TRANSAC_STTUS = 'CANCEL')**

 

##### (SagaService) PponContSagaService.java

·     

o   

§  

- MS1(samp.ppon) API와 관련된 SAGA 트랜잭션        비즈니스 로직을 작성한다.

§ 정상 트랜잭션 및 취소 트랜잭션 로직을 확인할 수 있다.

**PponContSagaService.java** 

```
// ...
 
@Component
@Slf4j
@RequiredArgsConstructor
public class PponContSagaService {
 
       // ...
 
       /**
        * <pre>
        * [regSagaApi] 일반전화 계약 등록
        * </pre>
        *
        * @param PponContDomain paramDomain
        * @param int            saPpontContNo
        * @return PponContDomain
        */
       public PponContDomain regPponCont(PponContDomain paramDomain, int saPpontContNo) {
 
              PponContDomain resultDomain = new PponContDomain();
 
              // 1. 일반전화 계약 등록
              paramDomain.setNew(true);
              paramDomain.setSaPponContNo(saPpontContNo);
        paramDomain.setTransacNo(CommonUtil.getCommonHeader().getGlobalNo());
              paramDomain.setTransacSttus("START");
              paramDomain.setDelYn("N");
              resultDomain = commandPponContRepository.save(paramDomain);
 
              return resultDomain;
       }   
 
       /**
        * <pre>
        * [regSagaApi] 일반전화 계약 등록 (취소처리)
        * </pre>
        *
        * @param int saPponContNo
        * @return void
        */
       public void regPponContCancel(int saPponContNo) {
 
              // 1. 일반전화 계약 등록 (취소처리)
              commandPponContRepository.regPponContCancel(saPponContNo);
 
              // DB 업데이트가 정상 처리되면 cache에 저장된 키 데이터를 삭제한다
              String key = Constant.PPON_CONT_KEY + saPponContNo;
              redisOperator.delete(key);
       }    
 
 
       // ...
 
}
```

**<****등록 트랜잭션>**

Line 29 : 일반전화(MS1) 계약 정보를 등록한다.

​        DB에 트랜잭션 '진행중' 상태(**TRANSAC_STTUS='START'**) 커밋한다.

 

**<****등록 취소 트랜잭션>**

Line 45 : 트랜잭션 진행중인 데이터를 취소처리한다.

​       등록에 대한 취소처리 (**DEL_YN='Y', TRANSAC_STTUS='CANCEL'**) 커밋한다.

 

 

##### (SagaService) InetaplcaContSagaService.java

·     

o   

§  

- MS2(samp.Inetaplca) API와 관련된 SAGA 트랜잭션        비즈니스 로직을 작성한다.

§ 정상트랜잭션 호출 및 보상트랜잭션 호출과 관련한 로직을 확인할 수 있다.

**InetaplcaContSagaService.java** 

```
// ...
 
@Component
@Slf4j
@RequiredArgsConstructor
public class InetaplcaContSagaService {
 
       // ...    
 
 
       /**
        * <pre>
        * [regSagaApi] 인터넷응용 계약 등록
        * </pre>
        *
        * @param PponContDomain paramDomain
        * @return SagaInetaplcaContOutDs
        */
       public SagaInetaplcaContOutDs regInetaplcaCont(PponContDomain paramDomain) {
 
              // 1. 인터넷응용 계약 등록 API 호출
              CommandInetaplcaContPayload commandInetaplcaContPayload = new CommandInetaplcaContPayload();
        commandInetaplcaContPayload.setSaPponContNo(paramDomain.getSaPponContNo());
           commandInetaplcaContPayload.setContr(paramDomain.getContr());
             commandInetaplcaContPayload.setPayr(paramDomain.getPayr());
           commandInetaplcaContPayload.setSvcNo(paramDomain.getSvcNo());
         commandInetaplcaContPayload.setCardNo(paramDomain.getCardNo());
 
              SagaInetaplcaContInDs sagaInetaplcaContInDs = new SagaInetaplcaContInDs();
        sagaInetaplcaContInDs.setInetaplcaContPayload(commandInetaplcaContPayload);
              SagaInetaplcaContOutDs sagaInetaplcaContOutDs = inetaplcaContSagaClient.regSagaApi(sagaInetaplcaContInDs);
 
              return sagaInetaplcaContOutDs;
       }
 
       /**
        * <pre>
        * [regSagaApi] 인터넷응용 계약 등록(보상처리)
        * </pre>
        *
        * @param int saInetaplcaContNo
        * @return SagaInetaplcaContOutDs
        */
       public SagaInetaplcaContOutDs regInetaplcaContCompensating(int saInetaplcaContNo) {
 
              // 1. 인터넷응용 계약 등록 보상처리 API 호출
              CommandInetaplcaContPayload commandInetaplcaContPayload = new CommandInetaplcaContPayload();
        commandInetaplcaContPayload.setSaInetaplcaContNo(saInetaplcaContNo);
 
              SagaInetaplcaContInDs sagaInetaplcaContInDs = new SagaInetaplcaContInDs();
        sagaInetaplcaContInDs.setInetaplcaContPayload(commandInetaplcaContPayload);
              SagaInetaplcaContOutDs sagaInetaplcaContOutDs = inetaplcaContSagaClient.regSagaApiConpensating(sagaInetaplcaContInDs);
 
              return sagaInetaplcaContOutDs;
       }   
 
 
       // ...
 
}
```

**<****등록 트랜잭션>**

Line 31 : 인터넷응용(MS2) 계약 정보 **등록을 위한 FeignClient를 호출**한다.

 

**<****등록 보상 트랜잭션>**

Line 52 : 인터넷응용(MS2) 계약 정보 **등록의 보상처리를 위한 FeignClient를 호출**한다.

 

##### (FeignClient) InetaplcaContSagaClient.java

·     

o   

§  

§ MS2(samp.Inetaplca) API 호출을 위한 FeignClient 를 작성한다.

**InetaplcaContSagaClient.java** 

```
// ...
 
@FeignClient(value = "InetaplcaContSagaClient", url = "${api.inetaplca.cont.url}", configuration = FeignSagaConfig.class)
public interface InetaplcaContSagaClient {
 
       // ...    
 
       /**
     * <pre>
     * [SAGA-api] 인터넷응용 계약 등록
     * </pre>
     *
     * @param SagaInetaplcaContInDs sagaInetaplcaContInDs
     * @return SagaInetaplcaContOutDs
     */
       @PostMapping(path = "/cont/regSagaApi")
       public SagaInetaplcaContOutDs regSagaApi(@RequestBody SagaInetaplcaContInDs sagaInetaplcaContInDs);   
 
 
       // ...   
 
 
       /**
     * <pre>
     * [SAGA-api] 인터넷응용 계약 등록 보상처리
     * </pre>
     *
     * @param SagaInetaplcaContInDs sagaInetaplcaContInDs
     * @return SagaInetaplcaContOutDs
     */
       @PostMapping(path = "/cont/regSagaApiConpensating")
       public SagaInetaplcaContOutDs regSagaApiConpensating(@RequestBody SagaInetaplcaContInDs sagaInetaplcaContInDs);   
 
 
       // ...
 
}
```

Line 16~17 : MS2(인터넷응용) 계약 등록 API를 호출한다.

Line 31~32 : MS2(인터넷응용) 계약 등록 보상처리 API를 호출한다.

 

##### (FeignClient) InetaplcaContSagaClientFallbackFactory.java

·     

o   

§  

§ MS2(samp.Inetaplca) API 호출의 Fallback 메서드 구현을 위한 FeignClientFallbackFactory 를 작성한다.

**InetaplcaContSagaClientFallbackFactory.java** 

```
// ...
 
@Slf4j
@Component
@RequiredArgsConstructor
public class InetaplcaContSagaClientFallbackFactory implements FallbackFactory<InetaplcaContSagaClient> {
 
    private final ContApiSagaManager contApiSagaManager;
 
       @Override
       public InetaplcaContSagaClient create(Throwable cause) {
           InterfaceException ex = (InterfaceException)cause;
              return new InetaplcaContSagaClient() {     
 
                      /**
                       * <pre>
                       * [SAGA-api] 인터넷응용 계약 등록
                       * </pre>
                       *
               * @param SagaInetaplcaContInDs sagaInetaplcaContInDs
               * @return SagaInetaplcaContOutDs
                       */
                      @Override
                      public SagaInetaplcaContOutDs regSagaApi(@RequestBody SagaInetaplcaContInDs sagaInetaplcaContInDs){
                             log.info("fallback called === [CommandInetaplcaContOutDs.regSagaApi] === ex.getResponseMessage : {}",ex.getResponseMessage());
 
                             // [MS-1] 일반전화 계약 등록 (취소처리)
                             // contApiSagaManager.regPponContCancel(commandInetaplcaContDs.getInetaplcaContPayload().getSaPponContNo());
 
                             return SagaInetaplcaContOutDs.builder()
                                      .sagaInetaplcaContPayload(SagaInetaplcaContPayload.builder().successYn("FALLBACK").message(ex.getResponseMessage().getResponseTitle()).build())
                                             .build();
                      }            
 
 
                      // ...         
 
 
                      /**
                       * <pre>
                       * [SAGA-api] 인터넷응용 계약 등록 보상처리
                       * </pre>
                       *
               * @param SagaInetaplcaContInDs sagaInetaplcaContInDs
               * @return SagaInetaplcaContOutDs
                       */
                      @Override
                      public SagaInetaplcaContOutDs regSagaApiConpensating(@RequestBody SagaInetaplcaContInDs sagaInetaplcaContInDs){
                             log.info("fallback called === [CommandInetaplcaContOutDs.regSagaApiConpensating] === ex.getResponseMessage : {}",ex.getResponseMessage());
 
                             return SagaInetaplcaContOutDs.builder()
                                      .sagaInetaplcaContPayload(SagaInetaplcaContPayload.builder().successYn("FALLBACK").message(ex.getResponseMessage().getResponseTitle()).build())
                                             .build();
                      }            
 
 
                      // ...
 
              };
       }
 
}
```

Line 24 : MS2(인터넷응용) 계약 등록 API 호출에 대한 Fallback 메서드를 작성한다.

Line 28 : MS2(인터넷응용) 계약 등록 트랜잭션 실패로 인한 

​       MS1(일반전화) 계약 등록 **취소처리를 호출**한다. (**ContApiSagaManager.java 에서 취소처리 구현해서 주석처리)

 

Line 48 : MS2(인터넷응용) 계약 등록 보상처리 API 호출에 대한 Fallback 메서드를 작성한다.

 

 

##### (SagaService) IntelnetContSagaService.java

·     

o   

§  

§ MS3(samp.intelnet) API와 관련된 SAGA 트랜잭션 비즈니스 로직을 작성한다.

**IntelnetContSagaService.java** 

```
// ...
 
@Component
@Slf4j
@RequiredArgsConstructor
public class IntelnetContSagaService {
 
       // ...   
 
       /**
        * <pre>
        * [regSagaApi] 지능망 계약 등록
        * </pre>
        *
        * @param PponContDomain paramDomain
        * @param int            saPpontContNo
        * @return SagaIntelnetContOutDs
        */
       public SagaIntelnetContOutDs regIntelnetCont(PponContDomain paramDomain) {
 
              // 1. 지능망 계약 등록 API 호출
              CommandIntelnetContPayload commandIntelnetContPayload = new CommandIntelnetContPayload();
        commandIntelnetContPayload.setSaPponContNo(paramDomain.getSaPponContNo());
            commandIntelnetContPayload.setContr(paramDomain.getContr());
              commandIntelnetContPayload.setPayr(paramDomain.getPayr());
            commandIntelnetContPayload.setSvcNo(paramDomain.getSvcNo());
          commandIntelnetContPayload.setCardNo(paramDomain.getCardNo());
 
              SagaIntelnetContInDs sagaIntelnetContInDs = new SagaIntelnetContInDs();
        sagaIntelnetContInDs.setIntelnetContPayload(commandIntelnetContPayload);
              SagaIntelnetContOutDs sagaIntelnetContOutDs = intelnetContSagaClient.regSagaApi(sagaIntelnetContInDs);
 
              return sagaIntelnetContOutDs;
       }   
 
       // ...
 
}
```

**<****등록 트랜잭션>**

Line 31 : 지능망(MS3) 계약 정보 **등록을 위한 FeignClient를 호출**한다.

 

##### (FeignClient) IntelnetContSagaClient.java

·     

o   

§  

§ MS3(samp.Intelnet) API 호출을 위한 FeignClient 를 작성한다.

**IntelnetContSagaClient.java** 

```
// ...
 
@FeignClient(value = "IntelnetContSagaClient", url = "${api.intelnet.cont.url}", configuration = FeignSagaConfig.class)
public interface IntelnetContSagaClient {
 
       // ...    
 
       /**
     * <pre>
     * [SAGA-api] 지능망 계약 등록
     * </pre>
     *
     * @param SagaIntelnetContInDs sagaIntelnetContInDs
     * @return SagaIntelnetContOutDs
     */
       @PostMapping(path = "/cont/regSagaApi")
       public SagaIntelnetContOutDs regSagaApi(@RequestBody SagaIntelnetContInDs sagaIntelnetContInDs);    
 
       // ...
 
}
```

##### (FeignClient) IntelnetContSagaClientFallbackFactory.java

·     

o   

§  

§ MS3(samp.Intelnet) API 호출의 Fallback 메서드 구현을 위한 FeignClientFallbackFactory 를 작성한다.

**InetaplcaContSagaClientFallbackFactory.java** 

```
// ...
 
@Slf4j
@Component
@RequiredArgsConstructor 
public class IntelnetContSagaClientFallbackFactory implements FallbackFactory<IntelnetContSagaClient> {
       
    private final ContApiSagaManager contApiSagaManager;
       
       @Override
       public IntelnetContSagaClient create(Throwable cause) {
           InterfaceException ex = (InterfaceException)cause;
              return new IntelnetContSagaClient() {     
 
                      /**
                       * <pre>
                       * [SAGA-api] 지능망 계약 등록
                       * </pre>
                       *
                       * @param SagaIntelnetContInDs sagaIntelnetContInDs
                       * @return SagaIntelnetContOutDs
                       */
                      @Override
                      public SagaIntelnetContOutDs regSagaApi(@RequestBody SagaIntelnetContInDs sagaIntelnetContInDs) {
                             log.info("fallback called === [CommandInetaplcaContOutDs.regSagaApi] === ex.getResponseMessage : {}",ex.getResponseMessage());
 
                             // List<PponContDto> pponContDto = queryPponContRepository.retvById(commandIntelnetContDs.getIntelnetContPayload().getSaPponContNo());
 
                             // // [MS-2] 인터넷응용 계약 등록 (보상처리)
                             // contApiSagaManager.regInetaplcaContCompensating(pponContDto.get(0).getSaInetaplcaContNo());
 
                             // // [MS-1] 일반전화 계약 등록 (취소처리)
                             // contApiSagaManager.regPponContCancel(pponContDto.get(0).getSaPponContNo());
 
                             return SagaIntelnetContOutDs.builder()
                                      .sagaIntelnetContPayload(SagaIntelnetContPayload.builder().successYn("FALLBACK").message(ex.getResponseMessage().getResponseTitle()).build())
                                             .build();
                      }         
 
                      // ...
 
              };
       }
 
}
```

Line 24 : MS3(지능망) 계약 등록 API 호출에 대한 Fallback 메서드를 작성한다.

Line 37~33 : MS3(지능망) 계약 등록 트랜잭션 실패로 인한 

​           MS2(인터넷응용) 계약 등록 **보상트랜잭션 호출** 및 MS1(일반전화) 계약 등록 **취소처리를 호출**한다. (**ContApiSagaManager.java 에서 취소처리 구현해서 주석처리)

 

 

#### MS2 (samp.inetaplca)

·     

o   

- Orchestraion       방식에서 MS2(samp.inetaplca) API는 MS1(samp.ppon)의 SAGA 정상       트랜잭션 및 보상 트랜잭션 요청을 처리하고 응답한다.

§ 프로젝트 구조는 다음과 같이 구성된다.

**Guide Project Structure** 

```
D:.
├─.mvn
├─.settings
├─bin
├─gitops
├─k8s
├─lib
├─logs
├─src
│  ├─main
│  │  ├─java
│  │  │  ├─com
│  │  │  │  └─kt
│  │  │  │      └─icis
│  │  │  │          ├─cmmn
│  │  │  │          ├─cmmnfrwk
│  │  │  │          └─samp
│  │  │  │              └─inetaplca
│  │  │  │                  ├─cont
│  │  │  │                  │  ├─command
│  │  │  │                  │  │  ├─endpoint        # Endpoint : SAGA API 메서드
│  │  │  │                  │  │  ├─payload
│  │  │  │                  │  │  ├─repository
│  │  │  │                  │  │  │  └─domain
│  │  │  │                  │  │  └─service
│  │  │  │                  │  └─query
│  │  │  │                  └─pubsub
│  │  │  └─org
│  │  └─resources
│  └─test
└─target
```

##### (Endpoint) CommandInetaplcaContEndpoint.java

·     

o   

§  

§ MS1(samp.ppon)의 SAGA 정상 트랜잭션 및 보상 트랜잭션 요청 처리를 위한 Endpoint를 작성한다.

**CommandInetaplcaContEndpoint.java** 

```
// ...
@RestController
@RequestMapping("/cont")
@RequiredArgsConstructor
@Tag(name = "인터넷응용 계약 Command", description = "인터넷응용 계약 - 등록/수정/삭제")
@Slf4j
public class CommandInetaplcaContEndpoint {
 
       // ...
 
       // [SAGA-Api] 호출
 
    /**
     * <pre>
     * [SAGA-Api] 인터넷응용 계약 등록
     * </pre>
     *
     * @param SagaInetaplcaContInDs sagaInetaplcaContInDs
     * @return SagaInetaplcaContOutDs
     */
    @Operation(summary = "[SAGA-Api] 인터넷응용 계약 등록", description = "인터넷응용 계약을 등록하는 기능입니다.")
    @PostMapping(path = "/regSagaApi")
    public SagaInetaplcaContOutDs regSagaApi(@RequestBody SagaInetaplcaContInDs sagaInetaplcaContInDs) throws Exception {
        log.info("[ICISTR:{}] === [CommandInetaplcaContEndpoint.regSagaApi] === [SAGA-Api] 인터넷응용 계약 등록 === requestBody : {}",CommonUtil.getCommonHeader().getGlobalNo(),sagaInetaplcaContInDs);
 
        SagaInetaplcaContOutDs sagaInetaplcaContOutDs = new SagaInetaplcaContOutDs();
        SagaInetaplcaContPayload sagaInetaplcaContPayload = new SagaInetaplcaContPayload();
 
        InetaplcaContDomain inetaplcaContDomain = commandInetaplcaContService.regSagaApi(sagaInetaplcaContInDs);
        if(inetaplcaContDomain != null){
            sagaInetaplcaContPayload = SagaInetaplcaContPayload.builder()
                                        .successYn("Y")
                                        .message("성공")
                                        .saPponContNo(inetaplcaContDomain.getSaPponContNo())
                                        .saInetaplcaContNo(inetaplcaContDomain.getSaInetaplcaContNo())
                                        .build();
        }
        else{
            sagaInetaplcaContPayload = SagaInetaplcaContPayload.builder()
                                        .successYn("N")
                                        .message("실패")
                                        .build();
        } 
        sagaInetaplcaContOutDs.setSagaInetaplcaContPayload(sagaInetaplcaContPayload);
 
        return sagaInetaplcaContOutDs;
    }    
 
 
       // ...
 
 
       /**
     * <pre>
     * [SAGA-Api] 인터넷응용 계약 등록 보상처리
     * </pre>
     *
     * @param SagaInetaplcaContInDs sagaInetaplcaContInDs
     * @return SagaInetaplcaContOutDs
     */
    @Operation(summary = "[SAGA-Api] 인터넷응용 계약 등록 보상처리", description = "인터넷응용 계약을 등록 보상처리하는 기능입니다.")
    @PostMapping(path = "/regSagaApiConpensating")
    public SagaInetaplcaContOutDs regSagaApiConpensating(@RequestBody SagaInetaplcaContInDs sagaInetaplcaContInDs) throws Exception {
        log.info("[ICISTR:{}] === [CommandInetaplcaContEndpoint.regSagaApiConpensating] === [SAGA-Api] 인터넷응용 계약 등록 보상처리 === requestBody : {}",CommonUtil.getCommonHeader().getGlobalNo(),sagaInetaplcaContInDs);
 
        SagaInetaplcaContOutDs sagaInetaplcaContOutDs = new SagaInetaplcaContOutDs();
        SagaInetaplcaContPayload sagaInetaplcaContPayload = new SagaInetaplcaContPayload();
 
        InetaplcaContDomain inetaplcaContDomain = commandInetaplcaContService.regSagaApiConpensating(sagaInetaplcaContInDs);
        
        if(inetaplcaContDomain != null){
            sagaInetaplcaContPayload = SagaInetaplcaContPayload.builder()
                                        .successYn("Y")
                                        .message("성공")
                                        .saPponContNo(inetaplcaContDomain.getSaPponContNo())
                                        .saInetaplcaContNo(inetaplcaContDomain.getSaInetaplcaContNo())
                                        .build();
        }
        else{
            sagaInetaplcaContPayload = SagaInetaplcaContPayload.builder()
                                        .successYn("N")
                                        .message("실패")
                                        .build();
        } 
        sagaInetaplcaContOutDs.setSagaInetaplcaContPayload(sagaInetaplcaContPayload);
 
        return sagaInetaplcaContOutDs;
    }    
 
 
       // ...
 
}
```

##### (Service) CommandInetaplcaContService.java

·     

o   

§  

§ MS2(samp.inetaplca) API와 관련된 SAGA 트랜잭션 비즈니스 로직을 작성한다.

**CommandInetaplcaContService.java** 

```
// ... 
@Service
@Slf4j
@RequiredArgsConstructor
public class CommandInetaplcaContService {
 
    // ...   
       /**
        * <pre>
        * [SAGA-Api] 인터넷응용 계약 등록
        * </pre>
        *
        * @param SagaInetaplcaContInDs sagaInetaplcaContInDs
        * @return InetaplcaContDomain
        */
       public InetaplcaContDomain regSagaApi(SagaInetaplcaContInDs sagaInetaplcaContInDs) throws Exception {
 
              InetaplcaContDomain resultDomain = new InetaplcaContDomain();
 
              // 인터넷응용 계약 등록
              InetaplcaContDomain paramDomain = InetaplcaContDomain.of(sagaInetaplcaContInDs.getInetaplcaContPayload());
              paramDomain.setNew(true);
              int saInetaplcaContNo = commandInetaplcaContRepository.nextVal();
              paramDomain.setSaInetaplcaContNo(saInetaplcaContNo);
              paramDomain.setDelYn("N");
        paramDomain.setTransacNo(CommonUtil.getCommonHeader().getGlobalNo());
              paramDomain.setTransacSttus("END");
              resultDomain = commandInetaplcaContRepository.save(paramDomain);
              log.info("[ICISTR:{}] === [CommandInetaplcaContService.regSagaApi] === 인터넷응용 계약 등록 sagaInetaplcaContInDs : {}",CommonUtil.getCommonHeader().getGlobalNo(),sagaInetaplcaContInDs);
 
              return resultDomain;
       }
 
       /**
        * <pre>
        * [SAGA-Api] 인터넷응용 계약 수정
        * </pre>
        *
        * @param SagaInetaplcaContInDs sagaInetaplcaContInDs
        * @return InetaplcaContDomain
        */
       public InetaplcaContDomain amdSagaApi(SagaInetaplcaContInDs sagaInetaplcaContInDs) throws Exception {
 
              InetaplcaContDomain resultDomain = new InetaplcaContDomain();
 
              // 인터넷응용 계약 이력 등록
              InetaplcaContHstDomain inetaplcaContHstDomain = regInetaplcaContHst(sagaInetaplcaContInDs.getInetaplcaContPayload());
 
              // 인터넷응용 계약 수정
              InetaplcaContDomain paramDomain = InetaplcaContDomain.of(sagaInetaplcaContInDs.getInetaplcaContPayload());
              paramDomain.setDelYn("N");
        paramDomain.setTransacNo(CommonUtil.getCommonHeader().getGlobalNo());
              paramDomain.setTransacSttus("END");
        paramDomain.setPrevSaInetaplcaContHstNo(inetaplcaContHstDomain.getSaInetaplcaContHstNo());
              resultDomain = commandInetaplcaContRepository.save(paramDomain);
              log.info("[ICISTR:{}] === [CommandInetaplcaContService.amdSagaApi] === 인터넷응용 계약 수정 sagaInetaplcaContInDs : {}",CommonUtil.getCommonHeader().getGlobalNo(),sagaInetaplcaContInDs);
 
              if (resultDomain != null) {
                      // DB 업데이트가 정상 처리되면 cache에 저장된 키 데이터를 삭제한다
                      String key = Constant.INETAPLCA_CONT_KEY + paramDomain.getSaInetaplcaContNo();
                      redisOperator.delete(key);
                      log.info("[ICISTR:{}] === [CommandInetaplcaContService.amdSagaApi] === 캐시 삭제 key : {}",CommonUtil.getCommonHeader().getGlobalNo(),key);
              }
 
              return resultDomain;
       }
 
       /**
        * <pre>
        * [SAGA-Api] 인터넷응용 계약 이력 등록
        * </pre>
        *
        * @param CommandInetaplcaContPayload commandInetaplcaContPayload
        * @return InetaplcaContHstDomain
        */
       private InetaplcaContHstDomain regInetaplcaContHst(CommandInetaplcaContPayload commandInetaplcaContPayload) throws Exception {
 
              InetaplcaContHstDomain resultDomain = new InetaplcaContHstDomain();
 
              List<InetaplcaContDto> inetaplcaContDto = queryInetaplcaContService.retvById(commandInetaplcaContPayload.getSaInetaplcaContNo());
              InetaplcaContHstDomain inetaplcaContHstDomain = InetaplcaContHstDomain.of(inetaplcaContDto.get(0));
              inetaplcaContHstDomain.setNew(true);
              int saInetaplcaContHstNo = commandInetaplcaContHstRepository.nextVal();
        inetaplcaContHstDomain.setSaInetaplcaContHstNo(saInetaplcaContHstNo);
              resultDomain = commandInetaplcaContHstRepository.save(inetaplcaContHstDomain);
              log.info("[ICISTR:{}] === [CommandInetaplcaContService.regInetaplcaContHst] === 인터넷응용 계약 이력 등록",CommonUtil.getCommonHeader().getGlobalNo());
 
              return resultDomain;
       }
 
 
 
   // ...
 
 
       
       /**
        * <pre>
        * [SAGA-Api] 인터넷응용 계약 등록 보상처리
        * </pre>
        *
        * @param SagaInetaplcaContInDs sagaInetaplcaContInDs
        * @return InetaplcaContDomain
        */
       @Transactional
       public InetaplcaContDomain regSagaApiCompensate(SagaInetaplcaContInDs sagaInetaplcaContInDs) throws Exception {
 
              InetaplcaContDomain resultDomain = new InetaplcaContDomain();
 
              // 인터넷응용 계약 등록 (보상처리)
        commandInetaplcaContRepository.regPponContCancel(sagaInetaplcaContInDs.getInetaplcaContPayload().getSaInetaplcaContNo());
              log.info("[ICISTR:{}] === [CommandInetaplcaContService.regSagaApiCompensate] === 인터넷응용 계약 등록 (보상처리) === sagaInetaplcaContInDs : {}",CommonUtil.getCommonHeader().getGlobalNo(),sagaInetaplcaContInDs);
 
              // DB 업데이트가 정상 처리되면 cache에 저장된 키 데이터를 삭제한다
              String key = Constant.INETAPLCA_CONT_KEY + sagaInetaplcaContInDs.getInetaplcaContPayload().getSaInetaplcaContNo();
              redisOperator.delete(key);
              log.info("[ICISTR:{}] === [CommandInetaplcaContService.regSagaApiCompensate] === 캐시 삭제 === key : {}",CommonUtil.getCommonHeader().getGlobalNo(),key);
 
              return resultDomain.of(queryInetaplcaContService.retvById(sagaInetaplcaContInDs.getInetaplcaContPayload().getSaInetaplcaContNo()).get(0));
       }
 
       /**
        * <pre>
        * [SAGA-Api] 인터넷응용 계약 수정 보상처리
        * </pre>
        *
        * @param SagaInetaplcaContInDs sagaInetaplcaContInDs
        * @return InetaplcaContDomain
        */
       @Transactional
       public InetaplcaContDomain amdSagaApiCompensate(SagaInetaplcaContInDs sagaInetaplcaContInDs) throws Exception {
 
              InetaplcaContDomain resultDomain = new InetaplcaContDomain();
 
              // 인터넷응용 계약 수정 보상처리
              List<InetaplcaContDto> inetaplcaContDto = queryInetaplcaContService.retvById(sagaInetaplcaContInDs.getInetaplcaContPayload().getSaInetaplcaContNo());
              List<InetaplcaContHstDto> inetaplcaContHstList = queryInetaplcaContService.retvHstById(inetaplcaContDto.get(0).getPrevSaInetaplcaContHstNo());
              InetaplcaContDomain inetaplcacontDomain = InetaplcaContDomain.of(inetaplcaContHstList.get(0));
        inetaplcacontDomain.setTransacNo(inetaplcaContDto.get(0).getTransacNo());
              inetaplcacontDomain.setTransacSttus("CANCEL");
              resultDomain = commandInetaplcaContRepository.save(inetaplcacontDomain);
              log.info("[ICISTR:{}] === [CommandInetaplcaContService.amdSagaApiCompensate] === 인터넷응용 계약 수정 (보상처리) === sagaInetaplcaContInDs : {}",CommonUtil.getCommonHeader().getGlobalNo(),sagaInetaplcaContInDs);
 
              // DB 업데이트가 정상 처리되면 cache에 저장된 키 데이터를 삭제한다
              String key = Constant.INETAPLCA_CONT_KEY + sagaInetaplcaContInDs.getInetaplcaContPayload().getSaInetaplcaContNo();
              redisOperator.delete(key);
              log.info("[ICISTR:{}] === [CommandInetaplcaContService.amdSagaApiCompensate] === 캐시 삭제 key : {}",CommonUtil.getCommonHeader().getGlobalNo(),key);
 
              return resultDomain;
       }
       
 
 
       // ...
 
 
 
}
```

#### MS3 (samp.intelnet)

·     

o   

- Orchestraion       방식에서 MS3(samp.inetaplca) API는 MS1(samp.ppon)의 SAGA 정상       트랜잭션 요청을 처리하고 응답한다.

§ 프로젝트 구조는 다음과 같이 구성된다.

**Guide Project Structure** 

```
D:.
├─.mvn
├─.settings
├─bin
├─gitops
├─k8s
├─lib
├─logs
├─src
│  ├─main
│  │  ├─java
│  │  │  ├─com
│  │  │  │  └─kt
│  │  │  │      └─icis
│  │  │  │          ├─cmmn
│  │  │  │          ├─cmmnfrwk
│  │  │  │          └─samp
│  │  │  │              └─intelnet
│  │  │  │                  ├─cont
│  │  │  │                  │  ├─command
│  │  │  │                  │  │  ├─endpoint        # Endpoint : SAGA API 메서드
│  │  │  │                  │  │  ├─payload
│  │  │  │                  │  │  ├─repository
│  │  │  │                  │  │  │  └─domain
│  │  │  │                  │  │  └─service         # Service
│  │  │  │                  │  └─query
│  │  │  │                  └─pubsub
│  │  │  └─org
│  │  └─resources
│  └─test
└─target
```

##### (Endpoint) CommandIntelnetContEndpoint.java

·     

o   

§  

§ MS1(samp.ppon)의 SAGA 정상 트랜잭션을 위한 Endpoint를 작성한다.

**CommandIntelnetContEndpoint.java** 

```
// ...
 
@RestController
@RequestMapping("/cont")
@RequiredArgsConstructor
@Tag(name = "지능망 계약 Command", description = "지능망 계약 - 등록/수정/삭제")
@Slf4j
public class CommandIntelnetContEndpoint {
 
       // ...
 
       /**
     * <pre>
     * [SAGA-Api] 지능망 계약 등록
     * </pre>
     *
     * @param SagaIntelnetContInDs sagaIntelnetContInDs
     * @return SagaIntelnetContOutDs
     */
    @Operation(summary = "[SAGA-Api] 지능망 계약 등록", description = "지능망 계약을 등록하는 기능입니다.")
    @PostMapping(path = "/regSagaApi")
    public SagaIntelnetContOutDs regSagaApi(@RequestBody SagaIntelnetContInDs sagaIntelnetContInDs) throws Exception {
        log.info("[ICISTR:{}] === [CommandIntelnetContEndpoint.regSagaApi] === [SAGA-Api] 지능망 계약 등록 === requestBody : {}",CommonUtil.getCommonHeader().getGlobalNo(),sagaIntelnetContInDs);
 
        SagaIntelnetContOutDs sagaIntelnetContOutDs = new SagaIntelnetContOutDs();
        SagaIntelnetContPayload sagaIntelnetContPayload = new SagaIntelnetContPayload();
 
        IntelnetContDomain intelnetContDomain = commandIntelnetContService.regSagaApi(sagaIntelnetContInDs);
        if(intelnetContDomain != null){
            sagaIntelnetContPayload = SagaIntelnetContPayload.builder()
                                        .successYn("Y")
                                        .message("성공")
                                        .saPponContNo(intelnetContDomain.getSaPponContNo())
                                        .saIntelnetContNo(intelnetContDomain.getSaIntelnetContNo())
                                        .build();
        }
        else{
            sagaIntelnetContPayload = SagaIntelnetContPayload.builder()
                                        .successYn("N")
                                        .message("실패")
                                        .build();
        } 
        sagaIntelnetContOutDs.setSagaIntelnetContPayload(sagaIntelnetContPayload);
 
        return sagaIntelnetContOutDs;
    }   
 
    /**
     * <pre>
     * [SAGA-Api] 지능망 계약 수정
     * </pre>
     *
     * @param SagaIntelnetContInDs sagaIntelnetContInDs
     * @return SagaIntelnetContOutDs
     */
    @Operation(summary = "[SAGA-Api] 지능망 계약 수정", description = "지능망 계약을 수정하는 기능입니다.")
    @PostMapping(path = "/amdSagaApi")
    public SagaIntelnetContOutDs amdSagaApi(@RequestBody SagaIntelnetContInDs sagaIntelnetContInDs) throws Exception {
        log.info("[ICISTR:{}] === [CommandIntelnetContEndpoint.amdSagaApi] === [SAGA-Api] 지능망 계약 수정 === requestBody : {}",CommonUtil.getCommonHeader().getGlobalNo(),sagaIntelnetContInDs);
 
        SagaIntelnetContOutDs sagaIntelnetContOutDs = new SagaIntelnetContOutDs();
        SagaIntelnetContPayload sagaIntelnetContPayload = new SagaIntelnetContPayload();
 
        IntelnetContDomain intelnetContDomain = commandIntelnetContService.amdSagaApi(sagaIntelnetContInDs);
        if(intelnetContDomain != null){
            sagaIntelnetContPayload = SagaIntelnetContPayload.builder()
                                        .successYn("Y")
                                        .message("성공")
                                        .saPponContNo(intelnetContDomain.getSaPponContNo())
                                        .saIntelnetContNo(intelnetContDomain.getSaIntelnetContNo())
                                        .build();
        }
        else{
            sagaIntelnetContPayload = SagaIntelnetContPayload.builder()
                                        .successYn("N")
                                        .message("실패")
                                        .build();
        } 
        sagaIntelnetContOutDs.setSagaIntelnetContPayload(sagaIntelnetContPayload);
 
        return sagaIntelnetContOutDs;
    }
 
 
       // ...
 
}
```

##### (Service) CommandIntelnetContService.java

·     

o   

§  

§ MS3(samp.intelnet) API와 관련된 SAGA 트랜잭션 비즈니스 로직을 작성한다.

**CommandIntelnetContService.java** 

```
// ...
@Service
@Slf4j
@RequiredArgsConstructor
public class CommandIntelnetContService {
 
 
       // ...
 
 
 
       /**
        * <pre>
        * [SAGA-Api] 지능망 계약 등록
        * </pre>
        *
        * @param SagaIntelnetContInDs sagaIntelnetContInDs
        * @return IntelnetContDomain
        */
       public IntelnetContDomain regSagaApi(SagaIntelnetContInDs sagaIntelnetContInDs) throws Exception {
 
              IntelnetContDomain resultDomain = new IntelnetContDomain();
 
              // 지능망 계약 등록
              IntelnetContDomain paramDomain = IntelnetContDomain.of(sagaIntelnetContInDs.getIntelnetContPayload());
              paramDomain.setNew(true);
              int saIntelnetContNo = commandIntelnetContRepository.nextVal();
              paramDomain.setSaIntelnetContNo(saIntelnetContNo);
              paramDomain.setDelYn("N");
        paramDomain.setTransacNo(CommonUtil.getCommonHeader().getGlobalNo());
              paramDomain.setTransacSttus("END");
              resultDomain = commandIntelnetContRepository.save(paramDomain);
              log.info("[ICISTR:{}] === [CommandIntelnetContService.regSagaApi] === 지능망 계약 등록 sagaIntelnetContInDs : {}",CommonUtil.getCommonHeader().getGlobalNo(),sagaIntelnetContInDs);
              
              return resultDomain;
       }
 
       /**
        * <pre>
        * [SAGA-Api] 지능망 계약 수정
        * </pre>
        *
        * @param SagaIntelnetContInDs sagaIntelnetContInDs
        * @return IntelnetContDomain
        */
       public IntelnetContDomain amdSagaApi(SagaIntelnetContInDs sagaIntelnetContInDs) throws Exception {
 
              IntelnetContDomain resultDomain = new IntelnetContDomain();
 
              // 지능망 계약 이력 등록
              IntelnetContHstDomain intelnetContHstDomain = regIntelnetContHst(sagaIntelnetContInDs.getIntelnetContPayload());
 
              // 지능망 계약 수정
              IntelnetContDomain paramDomain = IntelnetContDomain.of(sagaIntelnetContInDs.getIntelnetContPayload());
        paramDomain.setTransacNo(CommonUtil.getCommonHeader().getGlobalNo());
              paramDomain.setTransacSttus("END");
              paramDomain.setDelYn("N");
        paramDomain.setPrevSaIntelnetContHstNo(intelnetContHstDomain.getSaIntelnetContHstNo());
              resultDomain = commandIntelnetContRepository.save(paramDomain);
              log.info("[ICISTR:{}] === [CommandIntelnetContService.amdSagaApi] === 지능망 계약 수정 sagaIntelnetContInDs : {}",CommonUtil.getCommonHeader().getGlobalNo(),sagaIntelnetContInDs);
 
              if (resultDomain != null) {
                      // DB 업데이트가 정상 처리되면 cache에 저장된 키 데이터를 삭제한다
                      String key = Constant.INTELNET_CONT_KEY + paramDomain.getSaIntelnetContNo();
                      redisOperator.delete(key);
                      log.info("[ICISTR:{}] === [CommandIntelnetContService.amdSagaApi] === 캐시 삭제 key : {}",CommonUtil.getCommonHeader().getGlobalNo(),key);
              }
 
              return resultDomain;
       }
 
       /**
        * <pre>
        * [SAGA-Api] 지능망 계약 이력 등록
        * </pre>
        *
        * @param CommandIntelnetContPayload commandIntelnetContPayload
        * @return IntelnetContHstDomain
        */
       private IntelnetContHstDomain regIntelnetContHst(CommandIntelnetContPayload commandIntelnetContPayload) throws Exception {
 
              IntelnetContHstDomain resultDomain = new IntelnetContHstDomain();
 
              List<IntelnetContDto> intelnetContDto = queryIntelnetContService.retvById(commandIntelnetContPayload.getSaIntelnetContNo());
              IntelnetContHstDomain intelnetContHstDomain = IntelnetContHstDomain.of(intelnetContDto.get(0));
              intelnetContHstDomain.setNew(true);
              int saIntelnetContHstNo = commandIntelnetContHstRepository.nextVal();
        intelnetContHstDomain.setSaIntelnetContHstNo(saIntelnetContHstNo);
              resultDomain = commandIntelnetContHstRepository.save(intelnetContHstDomain);
              log.info("[ICISTR:{}] === [CommandIntelnetContService.regIntelnetContHst] === 지능망 계약 이력 등록",CommonUtil.getCommonHeader().getGlobalNo());
 
              return resultDomain;
       }
 
 
       // ...
 
 
 
 
}
```

### Message Broker 구현 방식

·     

o  Messasing Framework는 Kafka를 사용한다.

Kafka 가이드 페이지 - [[임시환경\] ICIS TR 1단계-SA-98-공용 Kafka 가이드]()

#### Message Broker 기반 SAGA 시나리오 (Ochestration)

##### 등록 시나리오 (성공)

![img](file:///C:/Users/kirobo/AppData/Local/Temp/msohtmlclip1/01/clip_image075.png)

1. 요청

 1-1. (통합UI) 계약 등록 요청

  \- KOS-B2C 구동 후 "샘플PA3 > ICIS-TR 1단계 샘플 > Event Sourcing - Kafka" 선택

  \- "행추가" 버튼 클릭 후, 그리드에 추가된 신규행에 값 입력

  \- 값 입력 후 "**등록**" 버튼 클릭하여 입력값 저장

​    ![img](file:///C:/Users/kirobo/AppData/Local/Temp/msohtmlclip1/01/clip_image077.png)

​       

 

 1-2. (MS1) 일반전화 계약 등록

  \- **INSERT INTO SA_PPON_CONT**

  \- SA_PPON_CONT_NO(일반전화 계약번호) : SA_PPON_CONT_SEQ.NEXTVAL

  \- DEL_YN(삭제유무) : 'N'

  \- TRANSAC_NO(트랜잭션 번호) : 통합UI globalNo

  \- TRANSAC_STTUS(트랜잭션 상태) : **'START'**

 

2. 메시지 발신 : MS2 트랜잭션 요청

 2-1. (MS1) 메시지 발신 : MS2 계약 등록 요청

  \- topic : sa-app-samp-ppon-cont-**inetaplcaContCreate**

  \- event : **globalNo(****통합UI globalNo)**

​         ,**eventName("inetaplcaContCreate")**

​         ,**saPponContNo(****등록된 일반전화 계약번호)**

​         ,contr(계약자)

​         ,payr(납입자)

​         ,svcNo(서비스번호)

​         ,cardNo(카드번호)

 

3. 응답

 3-1. (MS1) 계약 등록 정상 응답

 3-2. (통합UI) 응답 처리

  **- Kafka** **메시지 발신 후 비동기식으로 트랜잭션 처리되므로, 최종처리 후 응답이 아닌 것을 고려해야한다.**

![img](file:///C:/Users/kirobo/AppData/Local/Temp/msohtmlclip1/01/clip_image078.png)

 

4. 메시지 수신 : MS2 트랜잭션 요청

 4-1. (MS2) 메시지 수신 : MS2 계약 등록 요청

 4-2. (MS2) 인터넷응용 계약 등록

  \- **INSERT INTO** **SA_INETAPLCA_CONT**

  \- SA_INETAPLCA_CONT_NO(인터넷응용 계약번호) : SA_INETAPLCA_CONT_SEQ.NEXTVAL

  \- DEL_YN(삭제유무) : 'N'

  \- TRANSAC_NO(트랜잭션 번호) : 통합UI globalNo

  \- TRANSAC_STTUS(트랜잭션 상태) : **'END'**

 

5. 메시지 발신 : MS2 트랜잭션 응답

 5-1. (MS2) 메시지 발신 : MS2 계약 등록 완료 

  \- topic : sa-app-samp-ppon-cont-**ContResponse**

  \- event : globalNo(통합UI globalNo)

​         ,eventName("inetaplcaContCreate")

​         ,**successYn("Y")**

​         ,saPponContNo(일반전화 계약번호)

​         ,**saInetaplcaContNo(****등록된 인터넷응용 계약번호)**

 

6. 메시지 수신 : MS2 트랜잭션 응답

 6-1. (MS1) 메시지 수신 : MS2 계약 등록 완료

 6-2. (MS1) 인터넷응용 계약번호 UPDATE

  \- **UPDATE** **SA_PPON_CONT**

  \- SA_INETAPLCA_CONT_NO(인터넷응용 계약번호) : 트랜잭션 응답받은 인터넷응용 계약번호

 

7. 메시지 발신 : MS3 트랜잭션 요청

 7-1. (MS1) 메시지 발신 : MS3 계약 등록 요청

  \- topic : sa-app-samp-ppon-cont-**intelnetContCreate**

  \- event : **globalNo(****통합UI globalNo)**

​         ,**eventName("intelnetContCreate")**

​         ,**saPponContNo(****등록된 일반전화 계약번호)**

​         ,contr(계약자)

​         ,payr(납입자)

​         ,svcNo(서비스번호)

​         ,cardNo(카드번호)

 

8. 메시지 수신 : MS3 트랜잭션 요청

 8-1. (MS3) 메시지 수신 : MS3 계약 등록 요청

 8-2. (MS3) 지능망 계약 등록

  \- **INSERT INTO** **SA_INTELNET_CONT**

  \- SA_INTELNET_CONT_NO(지능망 계약번호) : SA_INTELNET_CONT_SEQ.NEXTVAL

  \- DEL_YN(삭제유무) : 'N'

  \- TRANSAC_NO(트랜잭션 번호) : 통합UI globalNo

  \- TRANSAC_STTUS(트랜잭션 상태) : **'END'**

 

9. 메시지 발신 : MS3 트랜잭션 응답

 9-1. (MS3) 메시지 발신 : MS3 계약 등록 완료 

  \- topic : sa-app-samp-ppon-cont-**ContResponse**

  \- event : globalNo(통합UI globalNo)

​         ,eventName("intelnetContCreate")

​         ,**successYn("Y")**

​         ,saPponContNo(일반전화 계약번호)

​         ,**saIntelnetContNo(****등록된 지능망 계약번호)**

 

10. 메시지 수신 : MS3 트랜잭션 응답

 10-1. (MS1) 메시지 수신 : MS3 계약 등록 완료

 10-2. (MS1) 지능망 계약번호 UPDATE

  \- **UPDATE** **SA_PPON_CONT**

  \- SA_INTELNET_CONT_NO(지능망 계약번호) : 트랜잭션 응답받은 지능망 계약번호

 10-3 . (MS1) 트랜잭션 상태 UPDATE

  \- **UPDATE** **SA_PPON_CONT**

  \- TRANSAC_STTUS(트랜잭션 상태) : **'END'**

 

 

 

##### 등록 시나리오 (보상)

![img](file:///C:/Users/kirobo/AppData/Local/Temp/msohtmlclip1/01/clip_image080.png)

1. 요청

 1-1. (통합UI) 계약 등록 요청

  \- KOS-B2C 구동 후 "샘플PA3 > ICIS-TR 1단계 샘플 > Event Sourcing - Kafka" 선택

  \- "행추가" 버튼 클릭 후, 그리드에 추가된 신규행에 값 입력

  \- 값 입력 후 "**등록**" 버튼 클릭하여 입력값 저장

 

 1-2. (MS1) 일반전화 계약 등록

  \- **INSERT INTO SA_PPON_CONT**

  \- SA_PPON_CONT_NO(일반전화 계약번호) : SA_PPON_CONT_SEQ.NEXTVAL

  \- DEL_YN(삭제유무) : 'N'

  \- TRANSAC_NO(트랜잭션 번호) : 통합UI globalNo

  \- TRANSAC_STTUS(트랜잭션 상태) : **'START'**

 

2. 메시지 발신 : MS2 트랜잭션 요청

 2-1. (MS1) 메시지 발신 : MS2 계약 등록 요청

  \- topic : sa-app-samp-ppon-cont-**inetaplcaContCreate**

  \- event : **globalNo(****통합UI globalNo)**

​         ,**eventName("inetaplcaContCreate")**

​         ,**saPponContNo(****등록된 일반전화 계약번호)**

​         ,contr(계약자)

​         ,payr(납입자)

​         ,svcNo(서비스번호)

​         ,cardNo(카드번호)

 

3. 응답

 3-1. (MS1) 계약 등록 정상 응답

 3-2. (통합UI) 응답 처리

  **- Kafka** **메시지 발신 후 비동기식으로 트랜잭션 처리되므로, 최종처리 후 응답이 아닌 것을 고려해야한다.**

 

4. 메시지 수신 : MS2 트랜잭션 요청

 4-1. (MS2) 메시지 수신 : MS2 계약 등록 요청

 4-2. (MS2) 인터넷응용 계약 등록

  \- **INSERT INTO** **SA_INETAPLCA_CONT**

  \- SA_INETAPLCA_CONT_NO(인터넷응용 계약번호) : SA_INETAPLCA_CONT_SEQ.NEXTVAL

  \- DEL_YN(삭제유무) : 'N'

  \- TRANSAC_NO(트랜잭션 번호) : 통합UI globalNo

  \- TRANSAC_STTUS(트랜잭션 상태) : **'END'**

 

5. 메시지 발신 : MS2 트랜잭션 응답

 5-1. (MS2) 메시지 발신 : MS2 계약 등록 완료 

  \- topic : sa-app-samp-ppon-cont-**ContResponse**

  \- event : globalNo(통합UI globalNo)

​         ,eventName("inetaplcaContCreate")

​         ,**successYn("Y")**

​         ,saPponContNo(일반전화 계약번호)

​         ,**saInetaplcaContNo(****등록된 인터넷응용 계약번호)**

 

6. 메시지 수신 : MS2 트랜잭션 응답

 6-1. (MS1) 메시지 수신 : MS2 계약 등록 완료

 6-2. (MS1) 인터넷응용 계약번호 UPDATE

  \- **UPDATE** **SA_PPON_CONT**

  \- SA_INETAPLCA_CONT_NO(인터넷응용 계약번호) : 트랜잭션 응답받은 인터넷응용 계약번호

 

7. 메시지 발신 : MS3 트랜잭션 요청

 7-1. (MS1) 메시지 발신 : MS3 계약 등록 요청

  \- topic : sa-app-samp-ppon-cont-**intelnetContCreate**

  \- event : **globalNo(****통합UI globalNo)**

​         ,**eventName("intelnetContCreate")**

​         ,**saPponContNo(****등록된 일반전화 계약번호)**

​         ,contr(계약자)

​         ,payr(납입자)

​         ,svcNo(서비스번호)

​         ,cardNo(카드번호)

 

8. 메시지 수신 : MS3 트랜잭션 요청

 8-1. (MS3) 메시지 수신 : MS3 계약 등록 요청

 8-2. (MS3) 지능망 계약 등록 중 **EXCEPTION** **발생**

 

9. 메시지 발신 : MS3 트랜잭션 응답

 9-1. **(MS3)** **메시지 발신 : MS3 계약 등록 실패** 

  \- topic : sa-app-samp-ppon-cont-**ContResponse**

  \- event : globalNo(통합UI globalNo)

​         ,eventName("intelnetContCreate")

​         ,**successYn("N")**

​         ,saPponContNo(일반전화 계약번호)

​         ,saIntelnetContNo('')

 

10. 메시지 수신 : MS3 트랜잭션 응답

 10-1. **(MS1)** **메시지 수신 : MS3 계약 등록 실패**

 

11. 메시지 발신 : MS2 보상 트랜잭션 요청

 11-1. **(MS1)** **메시지 발신 : MS2 계약 등록 보상처리**

  \- topic : sa-app-samp-ppon-cont-**inetaplcaContConpensate**

  \- event : **globalNo(****통합UI globalNo)**

​         ,**eventName("inetaplcaContCreateConpensate")**

​         ,**saPponContNo(****등록된 일반전화 계약번호)**

​         ,**saInetaplcaContNo(****등록된 인터넷응용 계약번호)**

 

12. 메시지 수신 : MS2 보상 트랜잭션 요청

 12-1. (MS2) 메시지 수신 : MS2 계약 등록 보상처리

 12-2. **(MS2)** **인터넷응용 계약 등록 보상처리**

  \- **UPDATE** **SA_INETAPLCA_CONT**

  \- DEL_YN(삭제유무) : **'Y'**

 

13. 메시지 발신 : MS2 보상 트랜잭션 응답

 13-1. **(MS2)** **메시지 발신 : MS2 계약 등록 보상처리 완료** 

  \- topic : sa-app-samp-ppon-cont-**ContResponse**

  \- event : globalNo(통합UI globalNo)

​         ,eventName("inetaplcaContCreateConpensate")

​         ,**successYn("Y")**

​         ,saPponContNo(일반전화 계약번호)

​         ,saInetaplcaContNo(인터넷응용 계약번호)

 

14. 메시지 수신 : MS2 보상 트랜잭션 응답

 14-1. (MS1) 메시지 수신 : MS2 계약 등록 보상처리 완료

 14-2. **(MS1)** **일반전화 계약 등록 취소처리**

  \- **UPDATE** **SA_PPON_CONT**

  \- DEL_YN(삭제유무) : **'Y'**

 

 

 

##### 수정 시나리오 (성공)

![img](file:///C:/Users/kirobo/AppData/Local/Temp/msohtmlclip1/01/clip_image082.png)

1. 요청

 1-1. (통합UI) 계약 등록 요청

  \- KOS-B2C 구동 후 "샘플PA3 > ICIS-TR 1단계 샘플 > Event Sourcing - Kafka" 선택

  \- 값 수정 후 "**수정**" 버튼 클릭하여 입력값 저장     

 

 1-2. (MS1) 일반전화 계약 이력 등록

  \- **INSERT INTO SA_PPON_CONT_HST**

  \- 취소 및 보상처리를 위한 **수정 전 원본 데이터 저장**

 

 1-3. (MS1) 일반전화 계약 수정

  \- **UPDATE SA_PPON_CONT**

  \- TRANSAC_STTUS(트랜잭션 상태) : **'START'**

  \- PREV_SA_PPON_CONT_HST_NO(이전 일반전화 계약 이력번호) : **1-2****에서 신규 생성된 시퀀스(SA_PPON_CONT_HST)**

 

2. 메시지 발신 : MS2 트랜잭션 요청

 2-1. (MS1) 메시지 발신 : MS2 계약 등록 요청

  \- topic : sa-app-samp-ppon-cont-**inetaplcaContUpdate**

  \- event : **globalNo(****통합UI globalNo)**

​         ,**eventName("inetaplcaContUpdate")**

​         ,**saPponContNo(****일반전화 계약번호)**

​         ,**saInetaplcaContNo(****인터넷응용 계약번호)**

​         ,contr(계약자)

​         ,payr(납입자)

​         ,svcNo(서비스번호)

​         ,cardNo(카드번호)

 

3. 응답

 3-1. (MS1) 계약 등록 정상 응답

 3-2. (통합UI) 응답 처리

  **- Kafka** **메시지 발신 후 비동기식으로 트랜잭션 처리되므로, 최종처리 후 응답이 아닌 것을 고려해야한다.**

 

4. 메시지 수신 : MS2 트랜잭션 요청

 4-1. (MS2) 메시지 수신 : MS2 계약 등록 요청

 

 4-2. (MS2) 인터넷응용 계약 이력 등록

  \- **INSERT INTO SA_INETAPLCA_CONT_HST**

  \- 취소 및 보상처리를 위한 **수정 전 원본 데이터 저장**

 

 4-3. (MS2) 인터넷응용 계약 수정

  \- **UPDATE**  **SA_INETAPLCA_CONT**

  \- TRANSAC_STTUS(트랜잭션 상태) : **'END'**

  \- PREV_SA_INETAPLCA_CONT_HST_NO(이전 인터넷응용 계약 이력번호) : **4-2****에서 신규 생성된 시퀀스(SA_PPON_CONT_HST)**

 

5. 메시지 발신 : MS2 트랜잭션 응답

 5-1. (MS2) 메시지 발신 : MS2 계약 등록 완료 

  \- topic : sa-app-samp-ppon-cont-**ContResponse**

  \- event : globalNo(통합UI globalNo)

​         ,eventName("inetaplcaContUpdate")

​         ,**successYn("Y")**

​         ,saPponContNo(일반전화 계약번호)

​         ,saInetaplcaContNo(인터넷응용 계약번호)

 

6. 메시지 수신 : MS2 트랜잭션 응답

 6-1. (MS1) 메시지 수신 : MS2 계약 등록 완료

 

7. 메시지 발신 : MS3 트랜잭션 요청

 7-1. (MS1) 메시지 발신 : MS3 계약 등록 요청

  \- topic : sa-app-samp-ppon-cont-**intelnetContUpdate**

  \- event : **globalNo(****통합UI globalNo)**

​         ,**eventName("intelnetContUpdate")**

​         ,**saPponContNo(****일반전화 계약번호)**

​         ,**saInetaplcaContNo(****인터넷응용 계약번호)**

​         ,contr(계약자)

​         ,payr(납입자)

​         ,svcNo(서비스번호)

​         ,cardNo(카드번호)

 

8. 메시지 수신 : MS3 트랜잭션 요청

 8-1. (MS3) 메시지 수신 : MS3 계약 등록 요청

 

 8-2. (MS3) 지능망 계약 이력 등록

  \- **INSERT INTO SA_INTELNET_CONT_HST**

  \- 취소 및 보상처리를 위한 **수정 전 원본 데이터 저장**

 

 8-3. (MS3) 지능망 계약 수정

  \- **UPDATE** **SA_INTELNET_CONT**

  \- TRANSAC_STTUS(트랜잭션 상태) : **'END'**

  \- PREV_SA_INTELNET_CONT_HST_NO(이전 지능망 계약 이력번호) : **8-2****에서 신규 생성된 시퀀스(SA_PPON_CONT_HST)**

 

9. 메시지 발신 : MS3 트랜잭션 응답

 9-1. (MS3) 메시지 발신 : MS3 계약 등록 완료 

  \- topic : sa-app-samp-ppon-cont-**ContResponse**

  \- event : globalNo(통합UI globalNo)

​         ,eventName("intelnetContCreate")

​         ,**successYn("Y")**

​         ,saPponContNo(일반전화 계약번호)

​         ,saIntelnetContNo(등록된 지능망 계약번호)

 

10. 메시지 수신 : MS3 트랜잭션 응답

 10-1. (MS1) 메시지 수신 : MS3 계약 등록 완료

 10-2. (MS1) 트랜잭션 상태 UPDATE

  \- **UPDATE** **SA_PPON_CONT**

  \- TRANSAC_STTUS(트랜잭션 상태) : **'END'**

 

 

 

##### 수정 시나리오 (보상)

![img](file:///C:/Users/kirobo/AppData/Local/Temp/msohtmlclip1/01/clip_image084.png)

1. 요청

 1-1. (통합UI) 계약 등록 요청

  \- KOS-B2C 구동 후 "샘플PA3 > ICIS-TR 1단계 샘플 > Event Sourcing - Kafka" 선택

  \- 값 수정 후 "**수정**" 버튼 클릭하여 입력값 저장     

 

 1-2. (MS1) 일반전화 계약 이력 등록

  \- **INSERT INTO SA_PPON_CONT_HST**

  \- 취소 및 보상처리를 위한 **수정 전 원본 데이터 저장**

 

 1-3. (MS1) 일반전화 계약 수정

  \- **UPDATE SA_PPON_CONT**

  \- TRANSAC_STTUS(트랜잭션 상태) : **'START'**

  \- PREV_SA_PPON_CONT_HST_NO(이전 일반전화 계약 이력번호) : **1-2****에서 신규 생성된 시퀀스(SA_PPON_CONT_HST)**

 

2. 메시지 발신 : MS2 트랜잭션 요청

 2-1. (MS1) 메시지 발신 : MS2 계약 등록 요청

  \- topic : sa-app-samp-ppon-cont-**inetaplcaContUpdate**

  \- event : **globalNo(****통합UI globalNo)**

​         ,**eventName("inetaplcaContUpdate")**

​         ,**saPponContNo(****일반전화 계약번호)**

​         ,**saInetaplcaContNo(****인터넷응용 계약번호)**

​         ,contr(계약자)

​         ,payr(납입자)

​         ,svcNo(서비스번호)

​         ,cardNo(카드번호)

 

3. 응답

 3-1. (MS1) 계약 등록 정상 응답

 3-2. (통합UI) 응답 처리

  **- Kafka** **메시지 발신 후 비동기식으로 트랜잭션 처리되므로, 최종처리 후 응답이 아닌 것을 고려해야한다.**

 

4. 메시지 수신 : MS2 트랜잭션 요청

 4-1. (MS2) 메시지 수신 : MS2 계약 등록 요청

 

 4-2. (MS2) 인터넷응용 계약 이력 등록

  \- **INSERT INTO SA_INETAPLCA_CONT_HST**

  \- 취소 및 보상처리를 위한 **수정 전 원본 데이터 저장**

 

 4-3. (MS2) 인터넷응용 계약 수정

  \- **UPDATE**  **SA_INETAPLCA_CONT**

  \- TRANSAC_STTUS(트랜잭션 상태) : **'END'**

  \- PREV_SA_INETAPLCA_CONT_HST_NO(이전 인터넷응용 계약 이력번호) : **4-2****에서 신규 생성된 시퀀스(SA_PPON_CONT_HST)**

 

5. 메시지 발신 : MS2 트랜잭션 응답

 5-1. (MS2) 메시지 발신 : MS2 계약 등록 완료 

  \- topic : sa-app-samp-ppon-cont-**ContResponse**

  \- event : globalNo(통합UI globalNo)

​         ,eventName("inetaplcaContUpdate")

​         ,**successYn("Y")**

​         ,saPponContNo(일반전화 계약번호)

​         ,saInetaplcaContNo(인터넷응용 계약번호)

 

6. 메시지 수신 : MS2 트랜잭션 응답

 6-1. (MS1) 메시지 수신 : MS2 계약 등록 완료

 

7. 메시지 발신 : MS3 트랜잭션 요청

 7-1. (MS1) 메시지 발신 : MS3 계약 등록 요청

  \- topic : sa-app-samp-ppon-cont-**intelnetContUpdate**

  \- event : **globalNo(****통합UI globalNo)**

​         ,**eventName("intelnetContUpdate")**

​         ,**saPponContNo(****일반전화 계약번호)**

​         ,**saInetaplcaContNo(****인터넷응용 계약번호)**

​         ,contr(계약자)

​         ,payr(납입자)

​         ,svcNo(서비스번호)

​         ,cardNo(카드번호)

 

8. 메시지 수신 : MS3 트랜잭션 요청

 8-1. (MS3) 메시지 수신 : MS3 계약 등록 요청

 8-2. (MS3) 지능망 계약 등록 중 **EXCEPTION** **발생**

 

9. 메시지 발신 : MS3 트랜잭션 응답

 9-1. **(MS3)** **메시지 발신 : MS3 계약 수정 실패** 

  \- topic : sa-app-samp-ppon-cont-**ContResponse**

  \- event : globalNo(통합UI globalNo)

​         ,eventName("intelnetContUpdate")

​         ,**successYn("N")**

​         ,saPponContNo(일반전화 계약번호)

​         ,saIntelnetContNo(인터넷응용 계약번호)

 

10. 메시지 수신 : MS3 트랜잭션 응답

 10-1. **(MS1)** **메시지 수신 : MS3 계약 등록 실패**

 

11. 메시지 발신 : MS2 보상 트랜잭션 요청

 11-1. **(MS1)** **메시지 발신 : MS2 계약 수정 보상처리**

  \- topic : sa-app-samp-ppon-cont-**inetaplcaContConpensate**

  \- event : **globalNo(****통합UI globalNo)**

​         ,**eventName("inetaplcaContUpdateConpensate")**

​         ,**saPponContNo(****일반전화 계약번호)**

​         ,**saInetaplcaContNo(****인터넷응용 계약번호)**

 

12. 메시지 수신 : MS2 보상 트랜잭션 요청

 12-1. (MS2) 메시지 수신 : MS2 계약 수정 보상처리

 12-2. **(MS2)** **인터넷응용 계약 수정 보상처리**

  \- **UPDATE** **SA_INETAPLCA_CONT**

​    **SELECT** ...

​    FROM **SA_INETAPLCA_CONT_HST**

​    WHERE **SA_INETAPLCA_CONT_HST_NO** = ( **SA_INETAPLCA_CONT** 의 **PREV_SA_INETAPLCA_CONT_HST_NO** )

 

13. 메시지 발신 : MS2 보상 트랜잭션 응답

 13-1. **(MS2)** **메시지 발신 : MS2 계약 수정 보상처리 완료** 

  \- topic : sa-app-samp-ppon-cont-**ContResponse**

  \- event : globalNo(통합UI globalNo)

​         ,eventName("inetaplcaContUpdateConpensate")

​         ,**successYn("Y")**

​         ,saPponContNo(일반전화 계약번호)

​         ,saInetaplcaContNo(인터넷응용 계약번호)

 

14. 메시지 수신 : MS2 보상 트랜잭션 응답

 14-1. (MS1) 메시지 수신 : MS2 계약 수정 보상처리 완료

 14-2. **(MS1)** **일반전화 계약 수정 취소처리**

  \- **UPDATE** **SA_PPON_CONT**

​    **SELECT** ...

​    FROM **SA_PPON_CONT_HST** 

​    WHERE **SA_PPON_CONT_HST_NO** = ( **SA_PPON_CONT** 의 **PREV_SA_PPON_CONT_HST_NO** )

 

 

#### MS1 (samp.ppon)

·     

o   

- Orchestraion       방식에서 MS1(samp.ppon) API는 오케스트레이터 역할을 수행한다.

§ 프로젝트 구조는 다음과 같이 구성된다.

**Guide Project Structure** 

```
D:.
├─.mvn
├─.settings
├─.vscode
├─bin
├─gitops
├─k8s
├─lib
├─logs
├─src
│  ├─main
│  │  ├─java
│  │  │  ├─com
│  │  │  │  └─kt
│  │  │  │      └─icis
│  │  │  │          ├─cmmn
│  │  │  │          ├─cmmnfrwk
│  │  │  │          └─samp
│  │  │  │              └─ppon
│  │  │  │                  ├─cont
│  │  │  │                  │  ├─command
│  │  │  │                  │  │  ├─endpoint         # Endpoint : 통합 UI 호출 메서드
│  │  │  │                  │  │  ├─payload
│  │  │  │                  │  │  ├─repository
│  │  │  │                  │  │  │  └─domain
│  │  │  │                  │  │  └─service                            
│  │  │  │                  │  └─query
│  │  │  │                  ├─exception
│  │  │  │                  ├─pubsub                  # SAGA Manager (Kafka 기반)
│  │  │  │                  │  ├─event                   # SAGA Kafka event 객체    
│  │  │  │                  │  ├─service                 # SAGA 서비스 구현
│  │  │  │                  │  └─stream
│  │  │  │                  │      └─function            # SAGA Kafka Producer, Consumer           
│  │  │  │                  └─saga                          
│  │  │  └─org
│  │  └─resources
│  └─test
└─target
```

##### (Endpoint) CommandPponContEndpoint.java

·     

o   

§  

§ Message Broker 기반 SAGA 요청을 처리하기 위한 Endpoint를 작성한다.

**CommandPponContEndpoint.java** 

```
// ...
@RestController
@RequestMapping("/cont")
@RequiredArgsConstructor
@Tag(name = "일반전화 계약 Command", description = "일반전화 계약 - 등록/수정/삭제")
@Slf4j
public class CommandPponContEndpoint {
 
       // ...   
 
       // =============================================================================================================
    // [SAGA-Event] 호출
    // =============================================================================================================
    /**
     * <pre>
     * [SAGA-Event] 일반전화 계약 등록
     * </pre>
     *
     * @param CommandPponContInDs commandPponContDs
     * @return CommandPponContOutDs
     */
    @Operation(summary = "[SAGA-Event] 일반전화 계약 등록", description = "일반전화 계약을 등록하는 기능입니다.")
    @PostMapping(path = "/regPubsub")
    public CommandPponContOutDs regPubsub(@RequestBody CommandPponContInDs commandPponContDs) throws Exception {
        log.info("[ICISTR:{}] === [CommandPponContEndpoint.regPubsub] === requestBody : {}",CommonUtil.getCommonHeader().getGlobalNo(), commandPponContDs);
 
        // SafeDb Test
        safeDbTest();
 
        // Cris Test
        crisTest(commandPponContDs);
 
        // Push Test
        pushTest();
 
        int result = contEventSagaManager.regSagaEvent(commandPponContDs,"N");
        return getResultMessage(result);
    }
 
    /**
     * <pre>
     * [SAGA-Event] 일반전화 계약 수정
     * </pre>
     *
     * @param CommandPponContInDs commandPponContDs
     * @return CommandPponContOutDs
     */
    @Operation(summary = "[SAGA-Event] 일반전화 계약 수정", description = "일반전화 계약을 수정하는 기능입니다.")
    @PostMapping(path = "/amdPubsub")
    public CommandPponContOutDs amdPubsub(@RequestBody CommandPponContInDs commandPponContDs) throws Exception {
        log.info("[ICISTR:{}] === [CommandPponContEndpoint.amdPubsub] === requestBody : {}",CommonUtil.getCommonHeader().getGlobalNo(), commandPponContDs);
 
        // SafeDb Test
        safeDbTest();
 
        // Cris Test
        crisTest(commandPponContDs);
 
        // Push Test
        pushTest();
 
        int result = contEventSagaManager.amdSagaEvent(commandPponContDs,"N");
        return getResultMessage(result);
    }   
 
 
       // ...
 
 
       // =============================================================================================================
    // [SAGA-Event] 보상트랜잭션 발생
    // =============================================================================================================
    /**
     * <pre>
     * [SAGA-Event] 일반전화 계약 등록 (보상트랜잭션 발생)
     * </pre>
     *
     * @param CommandPponContInDs commandPponContDs
     * @return CommandPponContOutDs
     */
    @Operation(summary = "[SAGA-Event] 일반전화 계약 등록 (보상트랜잭션 발생)", description = "일반전화 계약을 등록하는 기능입니다.")
    @PostMapping(path = "/regPubsubCompensate")
    public CommandPponContOutDs regPubsubCompensate(@RequestBody CommandPponContInDs commandPponContDs) throws Exception {
        log.info("[ICISTR:{}] === [CommandPponContEndpoint.regPubsub] === requestBody : {}",CommonUtil.getCommonHeader().getGlobalNo(), commandPponContDs);
 
        // SafeDb Test
        safeDbTest();
 
        // Cris Test
        crisTest(commandPponContDs);
 
        // Push Test
        pushTest();
 
        int result = contEventSagaManager.regSagaEvent(commandPponContDs,"Y");
        return getResultMessage(result);
    }
 
    /**
     * <pre>
     * [SAGA-Event] 일반전화 계약 수정 (보상트랜잭션 발생)
     * </pre>
     *
     * @param CommandPponContInDs commandPponContDs
     * @return CommandPponContOutDs
     */
    @Operation(summary = "[SAGA-Event] 일반전화 계약 수정 (보상트랜잭션 발생)", description = "일반전화 계약을 수정하는 기능입니다.")
    @PostMapping(path = "/amdPubsubCompensate")
    public CommandPponContOutDs amdPubsubCompensate(@RequestBody CommandPponContInDs commandPponContDs) throws Exception {
        log.info("[ICISTR:{}] === [CommandPponContEndpoint.amdPubsub] === requestBody : {}",CommonUtil.getCommonHeader().getGlobalNo(), commandPponContDs);
 
        // SafeDb Test
        safeDbTest();
 
        // Cris Test
        crisTest(commandPponContDs);
 
        // Push Test
        pushTest();
 
        int result = contEventSagaManager.amdSagaEvent(commandPponContDs,"Y");
        return getResultMessage(result);
    }  
 
 
       // ...
 
 
}
```

##### (SagaManager) ContEventSagaManager.java

·     

o   

§  

§ SAGA 트랜잭션 관리를 위한 SagaManager를 작성한다.

**ContEventSagaManager.java** 

```
// ...
@Component
@Slf4j
@RequiredArgsConstructor
public class ContEventSagaManager {
 
       // ...
 
 
       /**
        * <pre>
        * [SAGA-Event] [MS-1] 일반전화 계약 등록
        * </pre>
        *
        * @param CommandPponContInDs commandPponContDs
        * @return int
        */
       public int regSagaEvent(CommandPponContInDs commandPponContDs, String compensateYn) throws Exception {
 
              PponContDomain paramDomain = PponContDomain.of(commandPponContDs.getPponContPayload());
              int saPpontContNo = commandPponContRepository.nextVal();
 
              // =============================================================================================================
              // [MS-1] 일반전화 계약 등록
              // =============================================================================================================
              pponContEventSagaService.regPponCont(paramDomain, saPpontContNo);
              log.info("[ICISTR:{}] === [ContEventSagaManager.regSagaEvent] === [MS-1] 일반전화 계약 등록 commandPponContDs : {}",CommonUtil.getCommonHeader().getGlobalNo(), commandPponContDs);
 
              // =============================================================================================================
              // 메시지 발신 : [MS-2] 인터넷응용 등록 요청
              // =============================================================================================================
              try {
                      InetaplcaContCreateEvent event = new InetaplcaContCreateEvent();
 
                      // 보상트랜잭션 시나리오 : [MS-3] 트랜잭션 처리시 실패 메시지 응답처리
                      if("Y".equals(compensateYn)){
                             event = InetaplcaContCreateEvent.builder()
                                            .globalNo(CommonUtil.getCommonHeader().getGlobalNo())
                                            .eventName("inetaplcaContCreate")
                                            .saPponContNo(saPpontContNo)
                                            .contr(paramDomain.getContr())
                                            .payr(paramDomain.getPayr())
                                            .svcNo(paramDomain.getSvcNo())
                                            .cardNo(paramDomain.getCardNo())
                                             .compensateYn("Y")
                                             .build();
                      }
                      // 정상 시나리오
                      else{
                             event = InetaplcaContCreateEvent.builder()
                                            .globalNo(CommonUtil.getCommonHeader().getGlobalNo())
                                            .eventName("inetaplcaContCreate")
                                            .saPponContNo(saPpontContNo)
                                            .contr(paramDomain.getContr())
                                            .payr(paramDomain.getPayr())
                                            .svcNo(paramDomain.getSvcNo())
                                            .cardNo(paramDomain.getCardNo())
                                             .compensateYn("N")
                                             .build();
                      }
                     contEventProducer.inetaplcaContCreate(event);
                      log.info("[ICISTR:{}] === [ContEventSagaManager.regSagaEvent] === 메시지 발신 : [MS-2] 인터넷응용 등록 요청 === InetaplcaContCreateEvent : {}",CommonUtil.getCommonHeader().getGlobalNo(),event.toString());
       
                      return SUCCESS;
 
              } catch (Exception e) {
                      // [MS-1] 일반전화 계약 등록 (취소처리)
               regPponContCancel(saPpontContNo,CommonUtil.getCommonHeader().getGlobalNo());
 
                      throw e;
              }
       }
 
       /**
        * <pre>
        * [SAGA-Event] [MS-1] 일반전화 계약 수정
        * </pre>
        *
        * @param CommandPponContInDs commandPponContDs
        * @return int
        */
       @Transactional
       public int amdSagaEvent(CommandPponContInDs commandPponContDs, String compensateYn) throws Exception {
 
              PponContDomain paramDomain = PponContDomain.of(commandPponContDs.getPponContPayload());
 
              // =============================================================================================================
              // [MS-1] 일반전화 계약 이력 등록
              // =============================================================================================================
              PponContHstDomain pponContHstDomain = pponContEventSagaService.regPponContHst(paramDomain);
              log.info("[ICISTR:{}] === [ContEventSagaManager.amdSagaEvent] === [MS-1] 일반전화 계약 이력 등록 === paramDomain : {}",CommonUtil.getCommonHeader().getGlobalNo(), paramDomain);
 
              // =============================================================================================================
              // [MS-1] 일반전화 계약 수정
              // =============================================================================================================
              PponContDomain pponContDomain = pponContEventSagaService.amdPponCont(paramDomain, pponContHstDomain.getSaPponContHstNo());
              log.info("[ICISTR:{}] === [ContEventSagaManager.amdSagaEvent] === [MS-1] 일반전화 계약 수정 === paramDomain : {}, saPponContHstNo : {}",CommonUtil.getCommonHeader().getGlobalNo(), paramDomain, pponContHstDomain.getSaPponContHstNo());
 
              // =============================================================================================================
              // 메시지 발신 : [MS-2] 인터넷응용 수정 요청
              // =============================================================================================================
              try {
                      InetaplcaContUpdateEvent event = new InetaplcaContUpdateEvent();
 
                      // 보상트랜잭션 시나리오 : [MS-3] 트랜잭션 처리시 실패 메시지 응답처리
                      if("Y".equals(compensateYn)){
                             event = InetaplcaContUpdateEvent.builder()
                                            .globalNo(CommonUtil.getCommonHeader().getGlobalNo())
                                            .eventName("inetaplcaContUpdate")
                                            .saPponContNo(paramDomain.getSaPponContNo())
                                            .saInetaplcaContNo(paramDomain.getSaInetaplcaContNo())
                                            .contr(paramDomain.getContr())
                                            .payr(paramDomain.getPayr())
                                            .svcNo(paramDomain.getSvcNo())
                                            .cardNo(paramDomain.getCardNo())
                                             .compensateYn("Y")
                                             .build();
                      }
                      // 정상 시나리오
                      else{
                             event = InetaplcaContUpdateEvent.builder()
                                            .globalNo(CommonUtil.getCommonHeader().getGlobalNo())
                                            .eventName("inetaplcaContUpdate")
                                            .saPponContNo(paramDomain.getSaPponContNo())
                                            .saInetaplcaContNo(paramDomain.getSaInetaplcaContNo())
                                            .contr(paramDomain.getContr())
                                            .payr(paramDomain.getPayr())
                                            .svcNo(paramDomain.getSvcNo())
                                            .cardNo(paramDomain.getCardNo())
                                             .compensateYn("N")
                                             .build();
                      }
                     contEventProducer.inetaplcaContUpdate(event);
                      log.info("[ICISTR:{}] === [ContEventSagaManager.amdSagaEvent] === 메시지 발신 : [MS-2] 인터넷응용 수정 요청 === InetaplcaContUpdateEvent : {}",CommonUtil.getCommonHeader().getGlobalNo(),event.toString());
                      
                      return SUCCESS;
 
              } catch (Exception e) {
                      // [MS-1] 일반전화 계약 수정 (취소처리)
               amdPponContCancel(paramDomain.getSaPponContNo(),CommonUtil.getCommonHeader().getGlobalNo());
 
                      throw e;
              }
              
       }
 
 
 
       // ...
 
 
       /**
        * <pre>
        * 응답 event 수신 후 처리
        * </pre>
        *
        * @param ContResponseEvent event
        * @return void
        */
       public void contResponse(ContResponseEvent event) throws Exception {
 
        log.info("[ICISTR:{}] === [ContEventSagaManager.contResponse] === ContResponseEvent : {}",event.getGlobalNo(),event.toString());
 
              // =============================================================================================================
        // 1. 수신처리 : [MS-2] 인터넷응용 등록 응답
              // =============================================================================================================
        if("inetaplcaContCreate".equals(event.getEventName())){
 
                      // [MS-2] 인터넷응용 등록 트랜잭션 성공
                      if("Y".equals(event.getSuccessYn())){
 
                             try {
                                     // [MS-1] 일반전화 : 인터넷응용 계약번호 업데이트
                                     log.info("[ICISTR:{}] === [ContEventSagaManager.contResponse] === [MS-1] 일반전화 : 인터넷응용 계약번호 업데이트 === saInetaplcaContNo : {}",event.getGlobalNo(),event.getSaInetaplcaContNo());
                              commandPponContRepository.amdSaInetaplcaContNo(event.getSaPponContNo(), event.getSaInetaplcaContNo());
              
                                     // 메시지 발신 : [MS-3] 지능망 등록 요청
                                     IntelnetContCreateEvent intelnetContCreateEvent = IntelnetContCreateEvent.builder()
                                                                                                                                      .globalNo(event.getGlobalNo())
                                                                                                                                      .eventName("intelnetContCreate")
                                                                                                                                      .saPponContNo(event.getSaPponContNo())
                                                                                                                                      .contr(event.getContr())
                                                                                                                                      .payr(event.getPayr())
                                                                                                                                      .svcNo(event.getSvcNo())
                                                                                                                                      .cardNo(event.getCardNo())
                                                                                                                                      .compensateYn(event.getCompensateYn())
                                                                                                                                      .build();
                                contEventProducer.intelnetContCreate(intelnetContCreateEvent);
                                     log.info("[ICISTR:{}] === [ContEventSagaManager.contResponse] === 메시지 발신 : [MS-3] 지능망 등록 요청 === IntelnetContCreateEvent : {}",event.getGlobalNo(), intelnetContCreateEvent.toString());
 
                             } catch (Exception e) {
                                     // [MS-1] 일반전화 계약 등록 (취소처리)
                               regPponContCancel(event.getSaPponContNo(),event.getGlobalNo());
              
                                     throw e;
                             }
                             
                      }
                      // [MS-2] 인터넷응용 등록 트랜잭션 실패
                      else {
                             // [MS-1] 일반전화 계약 등록 (취소처리)
                        regPponContCancel(event.getSaPponContNo(),event.getGlobalNo());
                      }
            
        }
              // =============================================================================================================
        // 2. 수신처리 : [MS-2] 인터넷응용 수정 응답
        // =============================================================================================================
        else if("inetaplcaContUpdate".equals(event.getEventName())){
                      
                      // [MS-2] 인터넷응용 수정 트랜잭션 성공
                      if("Y".equals(event.getSuccessYn())){
                             try {
                                     // [MS-1] 지능망 계약번호 조회
                                     List<PponContDto> pponContDto = queryPponContRepository.retvById(event.getSaPponContNo());
                                     int saIntelnetContNo = pponContDto.get(0).getSaIntelnetContNo();
                                     log.info("[ICISTR:{}] === [ContEventSagaManager.contResponse] === [MS-1] 지능망 계약번호 조회 === saIntelnetContNo : {}",event.getGlobalNo(),saIntelnetContNo);
              
                                     // 메시지 발신 : [MS-3] 지능망 수정 요청
                                     IntelnetContUpdateEvent intelnetContUpdateEvent = IntelnetContUpdateEvent.builder()
                                                                                                                                      .globalNo(event.getGlobalNo())
                                                                                                                                      .eventName("intelnetContUpdate")
                                                                                                                                      .saPponContNo(event.getSaPponContNo())
                                                                                                                                      .saIntelnetContNo(saIntelnetContNo)
                                                                                                                                      .contr(event.getContr())
                                                                                                                                      .payr(event.getPayr())
                                                                                                                                      .svcNo(event.getSvcNo())
                                                                                                                                      .cardNo(event.getCardNo())
                                                                                                                                      .compensateYn(event.getCompensateYn())
                                                                                                                                      .build();
                                contEventProducer.intelnetContUpdate(intelnetContUpdateEvent);
                                     log.info("[ICISTR:{}] === [ContEventSagaManager.contResponse] === 메시지 발신 : [MS-3] 지능망 수정 요청 === IntelnetContUpdateEvent : {}",event.getGlobalNo(),intelnetContUpdateEvent.toString());
 
                             } catch (Exception e) {
                                     // [MS-1] 일반전화 계약 수정 (취소처리)
                               amdPponContCancel(event.getSaPponContNo(),event.getGlobalNo());
 
                                     throw e;
                             }
                      }
                      // [MS-2] 인터넷응용 수정 트랜잭션 실패
                      else{
                             // [MS-1] 일반전화 계약 수정 (취소처리)
                        amdPponContCancel(event.getSaPponContNo(),event.getGlobalNo());
                      }
 
        }
              
 
              // ...
 
 
              // =============================================================================================================
        // 4. 수신처리 : [MS-3] 지능망 등록 응답
              // =============================================================================================================
        else if("intelnetContCreate".equals(event.getEventName())){
 
                      // [MS-3] 지능망 등록 트랜잭션 성공
                      if("Y".equals(event.getSuccessYn())){
 
                             try {
                                     // [MS-1] 일반전화 : 지능망 계약번호 업데이트
                                     log.info("[ICISTR:{}] === [ContEventSagaManager.contResponse] === [MS-1] 일반전화 : 지능망 계약번호 업데이트 === saIntelnetContNo : {}",event.getGlobalNo(),event.getSaIntelnetContNo());
                              pponContEventSagaService.amdSaIntelnetContNo(event.getSaPponContNo(), event.getSaIntelnetContNo());
              
                                     // [MS-1] 일반전화 : 트랜잭션 처리상태 업데이트 (END)
                                     log.info("[ICISTR:{}] === [ContEventSagaManager.contResponse] === [MS-1] 일반전화 : 트랜잭션 처리상태 업데이트 (END)",event.getGlobalNo());
                              pponContEventSagaService.amdTransacSttus(event.getSaPponContNo());
              
                                     log.info("[ICISTR:{}] === [ContEventSagaManager.contResponse] === SAGA-kafaka-ContCreate 성공!!",event.getGlobalNo());
 
                             } catch (Exception e) {
                                     // [MS-2] 인터넷응용 계약 등록 (보상처리) 요청
                                    regInetaplcaContCompensate(event);
 
                                     throw e;
                             }
                      
                      }
                      // [MS-2] 지능망 등록 트랜잭션 실패
                      else{
                             // [MS-2] 인터넷응용 계약 등록 (보상처리)
                             regInetaplcaContCompensate(event);
 
                      }
        }
        // =============================================================================================================
              // 5. 수신처리 : [MS-3] 지능망 수정 응답
        // =============================================================================================================
              else if("intelnetContUpdate".equals(event.getEventName())){
 
                      // [MS-3] 지능망 수정 트랜잭션 성공
                      if("Y".equals(event.getSuccessYn())){
 
                             try {
                                     // [MS-1] 일반전화 : 트랜잭션 처리상태 업데이트 (END)
                                     log.info("[ICISTR:{}] === [ContEventSagaManager.contResponse] === [MS-1] 일반전화 : 트랜잭션 처리상태 업데이트 (END)",event.getGlobalNo());
                              pponContEventSagaService.amdTransacSttus(event.getSaPponContNo());
              
                                     log.info("[ICISTR:{}] === [ContEventSagaManager.contResponse] === SAGA-kafaka-ContUpdate 성공!!",event.getGlobalNo());
 
                             } catch (Exception e) {
                                     // [MS-2] 인터넷응용 계약 수정 (보상처리) 요청
                                    amdInetaplcaContCompensate(event);
 
                                     throw e;
                             }
                      
                      }
                      // [MS-2] 지능망 수정 트랜잭션 실패
                      else{
                             // [MS-2] 인터넷응용 계약 수정 (보상처리) 요청
                             amdInetaplcaContCompensate(event);
 
                      }
        }
              
 
              // ...
 
 
              // =============================================================================================================
        // 7-1. 수신처리 : [MS-2] 인터넷응용 등록 보상트랜잭션 응답
              // =============================================================================================================
              else if("inetaplcaContCreateCompensate".equals(event.getEventName())){
 
                      // [MS-2] 인터넷응용 등록 보상트랜잭션 성공
                      if("Y".equals(event.getSuccessYn())){
                             // [MS-1] 일반전화 계약 등록 (취소처리)
                        regPponContCancel(event.getSaPponContNo(),event.getGlobalNo());
                      }
                      // [MS-2] 인터넷응용 등록 보상트랜잭션 실패
                      else {
                             // 보상트랜잭션 실패시 처리..
                      }
            
        }
              // =============================================================================================================
        // 7-2. 수신처리 : [MS-2] 인터넷응용 수정 보상트랜잭션 응답
              // =============================================================================================================
              else if("inetaplcaContUpdateCompensate".equals(event.getEventName())){
                      
                      // [MS-2] 인터넷응용 수정 보상트랜잭션 성공
                      if("Y".equals(event.getSuccessYn())){
                             // [MS-1] 일반전화 계약 수정 (취소처리)
                        amdPponContCancel(event.getSaPponContNo(),event.getGlobalNo());
                      }
                      // [MS-2] 인터넷응용 수정 보상트랜잭션 실패
                      else{
                             // 보상트랜잭션 실패시 처리..
                      }
 
        }
              
 
              // ...
 
    }
 
 
 
       // ...
 
 
 
}
```

##### (SagaService) PponContSagaService.java

·     

o   

§  

- MS1(samp.ppon)        API와 관련된 SAGA 트랜잭션 비즈니스 로직을 작성한다.

§ 정상 트랜잭션 및 취소 트랜잭션 로직을 확인할 수 있다.

**PponContEventSagaService.java** 

```
// ...
@Component
@Slf4j
@RequiredArgsConstructor
public class PponContEventSagaService {
 
       // ...
 
 
       /**
        * <pre>
        * [SAGA-Event] 일반전화 계약 등록
        * </pre>
        *
        * @param PponContDomain paramDomain
        * @param int            saPpontContNo
        * @return PponContDomain
        */
       public PponContDomain regPponCont(PponContDomain paramDomain, int saPpontContNo) {
 
              PponContDomain resultDomain = new PponContDomain();
 
              // 일반전화 계약 등록
              paramDomain.setNew(true);
              paramDomain.setSaPponContNo(saPpontContNo);
              paramDomain.setDelYn("N");
        paramDomain.setTransacNo(CommonUtil.getCommonHeader().getGlobalNo());
              paramDomain.setTransacSttus("START");
              resultDomain = commandPponContRepository.save(paramDomain);
 
              return resultDomain;
       }
 
       /**
        * <pre>
        * [SAGA-Event] 일반전화 계약 등록 (취소처리)
        * </pre>
        *
        * @param int saPponContNo
        * @return void
        */
       public void regPponContCancel(int saPponContNo) {
 
              // 일반전화 계약 등록 (취소처리)
              commandPponContRepository.regPponContCancel(saPponContNo);
 
              // DB 업데이트가 정상 처리되면 cache에 저장된 키 데이터를 삭제한다
              String key = Constant.PPON_CONT_KEY + saPponContNo;
              redisOperator.delete(key);
       }
 
       /**
        * <pre>
        * [SAGA-Event] 일반전화 계약 이력 등록
        * </pre>
        *
        * @param PponContDomain paramDomain
        * @return PponContHstDomain
        */
       public PponContHstDomain regPponContHst(PponContDomain paramDomain) throws Exception {
 
              PponContHstDomain resultDomain = new PponContHstDomain();
 
              // 일반전화 계약 이력 등록
              List<PponContDto> PponContDtoList = queryPponContService.retvById(paramDomain.getSaPponContNo());
              PponContHstDomain pponContHstDomain = PponContHstDomain.of(PponContDtoList.get(0));
              pponContHstDomain.setNew(true);
              int saPpontContHstNo = commandPponContHstRepository.nextVal();
              pponContHstDomain.setSaPponContHstNo(saPpontContHstNo);
              resultDomain = commandPponContHstRepository.save(pponContHstDomain);
 
              return resultDomain;
       }
 
       /**
        * <pre>
        * [SAGA-Event] 일반전화 계약 수정
        * </pre>
        *
        * @param PponContDomain paramDomain
        * @return PponContDomain
        */
       public PponContDomain amdPponCont(PponContDomain paramDomain, int saPpontContHstNo) {
 
              PponContDomain resultDomain;
              paramDomain.setDelYn("N");
        paramDomain.setTransacNo(CommonUtil.getCommonHeader().getGlobalNo());
              paramDomain.setTransacSttus("START");
              paramDomain.setPrevSaPponContHstNo(saPpontContHstNo);
              resultDomain = commandPponContRepository.save(paramDomain);
 
              if (resultDomain != null) {
                      // DB 업데이트가 정상 처리되면 cache에 저장된 키 데이터를 삭제한다
                      String key = Constant.PPON_CONT_KEY + paramDomain.getSaPponContNo();
                      redisOperator.delete(key);
              }
              return resultDomain;
       }
 
       /**
        * <pre>
        * [SAGA-Event] 일반전화 계약 수정 (취소처리)
        * </pre>
        *
        * @param int saPponContNo
        * @return boolean
        */
       public boolean amdPponContCancel(int saPponContNo) throws Exception {
 
              // 일반전화 계약 수정 (취소처리)
              List<PponContDto> PponContDtoList = queryPponContService.retvById(saPponContNo);
              List<PponContHstDto> PponContHstDtoList = queryPponContService
                         .retvHstById(PponContDtoList.get(0).getPrevSaPponContHstNo());
              PponContDomain pponContDomain = PponContDomain.of(PponContHstDtoList.get(0));
        pponContDomain.setTransacNo(PponContDtoList.get(0).getTransacNo());
              pponContDomain.setTransacSttus("CANCEL");
              pponContDomain = commandPponContRepository.save(pponContDomain);
 
              if (pponContDomain != null) {
                      // DB 업데이트가 정상 처리되면 cache에 저장된 키 데이터를 삭제한다
                      String key = Constant.PPON_CONT_KEY + saPponContNo;
                      redisOperator.delete(key);
              }
 
              return true;
       }
 
 
 
       // ...
 
 
}
```

##### (Kafka:발신) ContEventProducer.java

·     

o   

§  

§ Kafka 메시지 발신을 위한 Event를 처리한다.

**CommandPponContEventService.java** 

```
// ... 
@Component
@Slf4j
@RequiredArgsConstructor
public class ContEventProducer {
 
       private final StreamBridge streamBridge;
       
       /**
        * <pre>
        * inetaplcaContCreate 이벤트 발신
        * </pre>
        *
        * @param InetaplcaContCreateEvent event
        * @return ResultMessage
        */
       public ResultMessage inetaplcaContCreate(InetaplcaContCreateEvent event) {
 
              boolean result = streamBridge.send("inetaplcaContCreate-out-0", event);
              log.info("[ICISTR:{}] === [CommandPponContEventService.inetaplcaContCreate] === [Producer] === 이벤트 발신 === InetaplcaContCreateEvent: {}, result : {}",event.getGlobalNo(), event, result);
 
              return getReturnResultMessage(result);
       }
 
       /**
        * <pre>
        * inetaplcaContUpdate 이벤트 발신
        * </pre>
        *
        * @param InetaplcaContUpdateEvent event
        * @return ResultMessage
        */
       public ResultMessage inetaplcaContUpdate(InetaplcaContUpdateEvent event) {
 
              boolean result = streamBridge.send("inetaplcaContUpdate-out-0", event);
              log.info("[ICISTR:{}] === [CommandPponContEventService.inetaplcaContUpdate] === [Producer] === 이벤트 발신 === InetaplcaContUpdateEvent: {}, result : {}",event.getGlobalNo(), event, result);
 
              return getReturnResultMessage(result);
       }
 
 
       // ... 
 
 
       /**
        * <pre>
        * intelnetContCreate 이벤트 발신
        * </pre>
        *
        * @param IntelnetContCreateEvent event
        * @return ResultMessage
        */
       public ResultMessage intelnetContCreate(IntelnetContCreateEvent event) {
 
              boolean result = streamBridge.send("intelnetContCreate-out-0", event);
              log.info("[ICISTR:{}] === [CommandPponContEventService.intelnetContCreate] === [Producer] === 이벤트 발신 === IntelnetContCreateEvent: {}, result : {}",event.getGlobalNo(), event, result);
              return getReturnResultMessage(result);
 
       }
 
       /**
        * <pre>
        * intelnetContUpdate 이벤트 발신
        * </pre>
        *
        * @param IntelnetContUpdateEvent event
        * @return ResultMessage
        */
       public ResultMessage intelnetContUpdate(IntelnetContUpdateEvent event) {
 
              boolean result = streamBridge.send("intelnetContUpdate-out-0", event);
              log.info("[ICISTR:{}] === [CommandPponContEventService.intelnetContUpdate] === [Producer] === 이벤트 발신 === IntelnetContUpdateEvent: {}, result : {}",event.getGlobalNo(), event, result);
              return getReturnResultMessage(result);
 
       }
 
 
       // ...
       
       /**
        * <pre>
        * inetaplcaContCompensate 이벤트 발신
        * </pre>
        *
        * @param inetaplcaContCompensate event
        * @return ResultMessage
        */
       public ResultMessage inetaplcaContCompensate(InetaplcaContCompensateEvent event) {
 
              boolean result = streamBridge.send("inetaplcaContCompensate-out-0", event);
              log.info("[ICISTR:{}] === [CommandPponContEventService.inetaplcaContCompensate] === [Producer] === 이벤트 발신 === InetaplcaContCompensateEvent: {}, result : {}",event.getGlobalNo(), event, result);
 
              return getReturnResultMessage(result);
       }
 
 
       // ...
 
}
```

Line 19 : MS2(인터넷응용) 계약 등록 요청을 위한 Topic(my-sa-app-samp-ppon-cont-inetaplcaContCreate)에 Event 발신 처리

​     \- Producer(발신) Topic 정보 확인 : (application-dev.yml) spring.cloud.stream.bindings.**inetaplcaContCreate-out-0**.destination: **my-sa-app-samp-ppon-cont-inetaplcaContCreate**

 

Line 55 : MS3(지능망) 계약 등록 요청을 위한 Topic(my-sa-app-samp-ppon-cont-intelnetContCreate)에 Event 발신 처리

​     \- Producer(발신) Topic 정보 확인 : (application-dev.yml) spring.cloud.stream.bindings.**intelnetContCreate-out-0**.destination: **my-sa-app-samp-ppon-cont-intelnetContCreate**

 

##### (Kafka:수신) ContEventConsumer.java

·     

o   

§  

§ Kafka 메시지 수신을 위한 Event를 처리한다.

**ContEventFunction.java** 

```
// ... 
@Configuration
@Slf4j
@RequiredArgsConstructor
public class ContEventConsumer {
 
    private Jackson2JsonObjectMapper mapper = new Jackson2JsonObjectMapper();
       private final ContEventSagaManager contEventSagaManager;
 
    @Bean
    public Consumer<String> contResponse() {
        return ((msg) -> {
            try {
                ContResponseEvent event = mapper.fromJson(msg, ContResponseEvent.class);
                log.info("[ICISTR:{}] === [ContEventFunction.contResponse] === [Consumer] 이벤트 수신 === ContResponseEvent : {}",event.getGlobalNo(),event.toString());
                
                // 이벤트 수신후 다음 SAGA 트랜잭션 호출
                contEventSagaManager.contResponse(event);
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
 
}
```

#### MS2 (samp.inetaplca)

·     

o   

§ 프로젝트 구조는 다음과 같이 구성된다.

```
§  D:.
§  ├─.mvn
§  ├─.settings
§  ├─.vscode
§  ├─bin
§  ├─gitops
§  ├─k8s
§  ├─lib
§  ├─logs
§  ├─src
§  │  ├─main
§  │  │  ├─java
§  │  │  │  ├─com
§  │  │  │  │  └─kt
§  │  │  │  │      └─icis
§  │  │  │  │          ├─cmmn
§  │  │  │  │          ├─cmmnfrwk
§  │  │  │  │          └─samp
§  │  │  │  │              └─inetaplca
§  │  │  │  │                  ├─cont
§  │  │  │  │                  │  ├─command
§  │  │  │  │                  │  └─query
§  │  │  │  │                  └─pubsub
§  │  │  │  │                      ├─event               # SAGA Kafka event 객체
§  │  │  │  │                      ├─service             # SAGA 서비스 구현
§  │  │  │  │                      └─stream
§  │  │  │  │                          └─function        # SAGA Kafka Producer, Consumer
§  │  │  │  └─org
§  │  │  └─resources
§  │  └─test
└─target
```

##### (Kafka:수신) ContEventConsumer.java

·     

o   

§  

§ Kafka 메시지 수신을 위한 Event를 처리한다.

**ContEventConsumer.java** 

```
// ...
@Configuration
@Slf4j
@RequiredArgsConstructor
public class ContEventConsumer {
 
    private Jackson2JsonObjectMapper mapper = new Jackson2JsonObjectMapper();
       private final InetaplcaContEventSagaService inetaplcaContEventSagaService;
 
    @Bean
    public Consumer<Message<String>> inetaplcaContCreate() {
        return ((msg) -> {
            try {
                InetaplcaContCreateEvent event = mapper.fromJson(msg.getPayload(), InetaplcaContCreateEvent.class);
                log.info("[ICISTR:{}] === [ContEventConsumer.inetaplcaContCreate] === [Consumer] 이벤트 수신 === InetaplcaContCreateEvent : {}",event.getGlobalNo(),event.toString());
                
                // 인터넷응용 계약 등록
                inetaplcaContEventSagaService.inetaplcaContCreate(event);
                
                Acknowledgment acknowledgment = msg.getHeaders().get(KafkaHeaders.ACKNOWLEDGMENT, Acknowledgment.class);           
                if(acknowledgment != null){
                    log.info("[ICISTR:{}] === [ContEventConsumer.inetaplcaContCreate] === [Consumer] Acknowledgment provided === ",event.getGlobalNo());
                    acknowledgment.acknowledge();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
 
    @Bean
    @Transactional
    public Consumer<Message<String>> inetaplcaContUpdate() {
        return ((msg) -> {
            try {
                InetaplcaContUpdateEvent event = mapper.fromJson(msg.getPayload(), InetaplcaContUpdateEvent.class);
                log.info("[ICISTR:{}] === [ContEventConsumer.inetaplcaContUpdate] === [Consumer] 이벤트 수신 === InetaplcaContUpdateEvent : {}",event.getGlobalNo(),event.toString());
                
                // 인터넷응용 계약 수정
                inetaplcaContEventSagaService.inetaplcaContUpdate(event);
                
                Acknowledgment acknowledgment = msg.getHeaders().get(KafkaHeaders.ACKNOWLEDGMENT, Acknowledgment.class);           
                if(acknowledgment != null){
                    log.info("[ICISTR:{}] === [ContEventConsumer.inetaplcaContUpdate] === [Consumer] Acknowledgment provided === ",event.getGlobalNo());
                    acknowledgment.acknowledge();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
 
    
       // ...
 
 
    @Bean
    public Consumer<Message<String>> inetaplcaContCompensate() {
        return ((msg) -> {
            try {
                InetaplcaContCompensateEvent event = mapper.fromJson(msg.getPayload(), InetaplcaContCompensateEvent.class);
                log.info("[ICISTR:{}] === [ContEventConsumer.inetaplcaContCompensate] === [Consumer] 이벤트 수신 === InetaplcaContCompensateEvent : {}",event.getGlobalNo(),event.toString());
                
                // 인터넷응용 계약 등록 보상처리
                if("inetaplcaContCreateCompensate".equals(event.getEventName())) {
                    inetaplcaContEventSagaService.inetaplcaContCreateCompensate(event);
                }
                // 인터넷응용 계약 수정 보상처리
                else if("inetaplcaContUpdateCompensate".equals(event.getEventName())) {
                    inetaplcaContEventSagaService.inetaplcaContUpdateCompensate(event);
                }
                // 인터넷응용 계약 삭제 보상처리
                else if("inetaplcaContDeleteCompensate".equals(event.getEventName())) {
                    inetaplcaContEventSagaService.inetaplcaContDeleteCompensate(event);
                }
                
                Acknowledgment acknowledgment = msg.getHeaders().get(KafkaHeaders.ACKNOWLEDGMENT, Acknowledgment.class);           
                if(acknowledgment != null){
                    log.info("[ICISTR:{}] === [ContEventConsumer.inetaplcaContCompensate] === [Consumer] Acknowledgment provided === ",event.getGlobalNo());
                    acknowledgment.acknowledge();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
 
    }
 
    
}
```

##### (SagaService) InetaplcaContEventSagaService.java

·     

o   

§  

- MS2(samp.inetaplca)        API와 관련된 SAGA 트랜잭션 비즈니스 로직을 작성한다.

§ 정상 트랜잭션 및 취소 트랜잭션 로직을 확인할 수 있다.

**IntelnetContEventSagaService.java** 

```
// ...
@Component
@Slf4j
@RequiredArgsConstructor
public class InetaplcaContEventSagaService {
 
       // ...
 
       
       /**
     * <pre>
     * [SAGA-Event] 인터넷응용 계약 등록 트랜잭션
     * </pre>
     *
     * @param InetaplcaContCreateEvent event
     * @return void
     */
    public void inetaplcaContCreate(InetaplcaContCreateEvent event) {
 
        log.info("[ICISTR:{}] === [InetaplcaContEventSagaService.inetaplcaContCreate] === 인터넷응용 계약 등록 트랜잭션 === InetaplcaContCreateEvent : {}",event.getGlobalNo(),event.toString());
 
        try {
            // 인터넷응용 계약 등록
            InetaplcaContDomain inetaplcaDomain = new InetaplcaContDomain();
            inetaplcaDomain.setNew(true);
            int saInetaplcaContNo = commandInetaplcaContRepository.nextVal();
            inetaplcaDomain.setSaInetaplcaContNo(saInetaplcaContNo);
            inetaplcaDomain.setSaPponContNo(event.getSaPponContNo());
            inetaplcaDomain.setContr(event.getContr());
            inetaplcaDomain.setPayr(event.getPayr());
            inetaplcaDomain.setSvcNo(event.getSvcNo());
            inetaplcaDomain.setCardNo(event.getCardNo());
            inetaplcaDomain.setDelYn("N");
            inetaplcaDomain.setTransacNo(event.getGlobalNo());
            inetaplcaDomain.setTransacSttus("END");
            commandInetaplcaContRepository.save(inetaplcaDomain);
            log.info("[ICISTR:{}] === [InetaplcaContEventSagaService.inetaplcaContCreate] === 인터넷응용 계약 등록",event.getGlobalNo());
 
            // 메시지 발신 : 인터넷응용 등록 응답 (성공)
            ContResponseEvent contResponseEvent = ContResponseEvent.builder()
                                                    .globalNo(event.getGlobalNo())
                                                    .eventName("inetaplcaContCreate")
                                                    .successYn("Y")
                                                    .saPponContNo(event.getSaPponContNo())
                                                    .saInetaplcaContNo(saInetaplcaContNo)
                                                    .contr(event.getContr())
                                                    .payr(event.getPayr())
                                                    .svcNo(event.getSvcNo())
                                                    .cardNo(event.getCardNo())
                                                    .compensateYn(event.getCompensateYn())
                                                    .build();
            contEventProducer.contResponse(contResponseEvent);
            log.info("[ICISTR:{}] === [InetaplcaContEventSagaService.inetaplcaContCreate] === 메시지 발신 : 인터넷응용 등록 응답 (성공) === ContResponseEvent : {}",event.getGlobalNo(),contResponseEvent.toString());
 
        } catch (Exception e) {
 
            // 메시지 발신 : 인터넷응용 등록 응답 (실패)
            ContResponseEvent contResponseEvent = ContResponseEvent.builder()
                                                    .globalNo(event.getGlobalNo())
                                                    .eventName("inetaplcaContCreate")
                                                    .successYn("Y")
                                                    .saPponContNo(event.getSaPponContNo())
                                                    .contr(event.getContr())
                                                    .payr(event.getPayr())
                                                    .svcNo(event.getSvcNo())
                                                    .cardNo(event.getCardNo())
                                                    .compensateYn(event.getCompensateYn())
                                                    .build();
            contEventProducer.contResponse(contResponseEvent);
            log.info("[ICISTR:{}] === [InetaplcaContEventSagaService.inetaplcaContCreate] === 메시지 발신 : 인터넷응용 등록 응답 (실패) === ContResponseEvent : {}",event.getGlobalNo(),contResponseEvent.toString());
            
        }
        
    }
 
    /**
     * <pre>
     * [SAGA-Event] 인터넷응용 계약 수정 트랜잭션
     * </pre>
     *
     * @param InetaplcaContUpdateEvent event
     * @return void
     */
    public void inetaplcaContUpdate(InetaplcaContUpdateEvent event) throws Exception {
 
        log.info("[ICISTR:{}] === [InetaplcaContEventSagaService.inetaplcaContUpdate] === 인터넷응용 계약 수정 트랜잭션 === InetaplcaContUpdateEvent : {}",event.getGlobalNo(),event.toString());
 
        try {
            
            // 인터넷응용 계약 이력 등록
            List<InetaplcaContDto> inetaplcaContDto = queryInetaplcaContRepository.retvById(event.getSaInetaplcaContNo());
            InetaplcaContHstDomain inetaplcaContHstDomain = InetaplcaContHstDomain.of(inetaplcaContDto.get(0));
            inetaplcaContHstDomain.setNew(true);
            int saPpontContHstNo = commandInetaplcaContHstRepository.nextVal();
            inetaplcaContHstDomain.setSaInetaplcaContHstNo(saPpontContHstNo);
            commandInetaplcaContHstRepository.save(inetaplcaContHstDomain);
            log.info("[ICISTR:{}] === [InetaplcaContEventSagaService.inetaplcaContUpdate] === 인터넷응용 계약 이력 등록",event.getGlobalNo());
 
            // 인터넷응용 계약 수정
            InetaplcaContDomain inetaplcaDomain = new InetaplcaContDomain();
            inetaplcaDomain.setSaInetaplcaContNo(event.getSaInetaplcaContNo());
            inetaplcaDomain.setSaPponContNo(event.getSaPponContNo());
            inetaplcaDomain.setContr(event.getContr());
            inetaplcaDomain.setPayr(event.getPayr());
            inetaplcaDomain.setSvcNo(event.getSvcNo());
            inetaplcaDomain.setCardNo(event.getCardNo());
            inetaplcaDomain.setDelYn("N");
            inetaplcaDomain.setTransacNo(event.getGlobalNo());
            inetaplcaDomain.setTransacSttus("END");
            InetaplcaContDomain inetaplcaContDomain = commandInetaplcaContRepository.save(inetaplcaDomain);
            log.info("[ICISTR:{}] === [InetaplcaContEventSagaService.inetaplcaContUpdate] === 인터넷응용 계약 수정",event.getGlobalNo());
 
            // 메시지 발신 : 인터넷응용 수정 응답 (성공)
            ContResponseEvent contResponseEvent = ContResponseEvent.builder()
                                                    .globalNo(event.getGlobalNo())
                                                    .eventName("inetaplcaContUpdate")
                                                    .successYn("Y")
                                                    .saPponContNo(event.getSaPponContNo())
                                                    .saInetaplcaContNo(event.getSaInetaplcaContNo())
                                                    .contr(event.getContr())
                                                    .payr(event.getPayr())
                                                    .svcNo(event.getSvcNo())
                                                    .cardNo(event.getCardNo())
                                                    .compensateYn(event.getCompensateYn())
                                                    .build();
            contEventProducer.contResponse(contResponseEvent);
            log.info("[ICISTR:{}] === [InetaplcaContEventSagaService.inetaplcaContUpdate] === 메시지 발신 : 인터넷응용 수정 응답 (성공) === ContResponseEvent : {}",event.getGlobalNo(),contResponseEvent.toString());
 
        } catch (Exception e) {
 
            // 메시지 발신 : 인터넷응용 수정 응답 (실패)
            ContResponseEvent contResponseEvent = ContResponseEvent.builder()
                                                    .globalNo(event.getGlobalNo())
                                                    .eventName("inetaplcaContUpdate")
                                                    .successYn("N")
                                                    .saPponContNo(event.getSaPponContNo())
                                                    .saInetaplcaContNo(event.getSaInetaplcaContNo())
                                                    .contr(event.getContr())
                                                    .payr(event.getPayr())
                                                    .svcNo(event.getSvcNo())
                                                    .cardNo(event.getCardNo())
                                                    .compensateYn(event.getCompensateYn())
                                                    .build();
            contEventProducer.contResponse(contResponseEvent);
            log.info("[ICISTR:{}] === [InetaplcaContEventSagaService.inetaplcaContUpdate] === 메시지 발신 : 인터넷응용 수정 응답 (실패) === ContResponseEvent : {}",event.getGlobalNo(),contResponseEvent.toString());
            
        }
    }
    
 
 
       // ...
 
 
 
    /**
     * <pre>
     * [SAGA-Event] 인터넷응용 계약 등록 보상처리 트랜잭션
     * </pre>
     *
     * @param InetaplcaContCompensateEvent event
     * @return void
     */
    public void inetaplcaContCreateCompensate(InetaplcaContCompensateEvent event) {
 
        log.info("[ICISTR:{}] === [InetaplcaContEventSagaService.inetaplcaContCreateCompensate] === 인터넷응용 계약 등록 보상처리 트랜잭션 === InetaplcaContCreateEvent : {}",event.getGlobalNo(),event.toString());
 
        try {
            // 인터넷응용 계약 등록 (보상처리)
            commandInetaplcaContRepository.regPponContCancel(event.getSaInetaplcaContNo());
            log.info("[ICISTR:{}] === [InetaplcaContEventSagaService.inetaplcaContCreateCompensate] === 인터넷응용 계약 등록 (보상처리) === sagaInetaplcaContInDs : {}",event.getGlobalNo(),event.getSaInetaplcaContNo());
 
            // DB 업데이트가 정상 처리되면 cache에 저장된 키 데이터를 삭제한다
            String key = Constant.INETAPLCA_CONT_KEY + event.getSaInetaplcaContNo();
            redisOperator.delete(key);
            log.info("[ICISTR:{}] === [InetaplcaContEventSagaService.inetaplcaContCreateCompensate] === 캐시 삭제 === key : {}",event.getGlobalNo(),key);
 
            // 메시지 발신 : 인터넷응용 등록 보상처리 응답 (성공)
            ContResponseEvent contResponseEvent = ContResponseEvent.builder()
                                                    .globalNo(event.getGlobalNo())
                                                    .eventName("inetaplcaContCreateCompensate")
                                                    .successYn("Y")
                                                    .saPponContNo(event.getSaPponContNo())
                                                    .saInetaplcaContNo(event.getSaInetaplcaContNo())
                                                    .build();
            contEventProducer.contResponse(contResponseEvent);
            log.info("[ICISTR:{}] === [InetaplcaContEventSagaService.inetaplcaContCreateCompensate] === 메시지 발신 : 인터넷응용 등록 보상처리 응답 (성공) === ContResponseEvent : {}",event.getGlobalNo(),contResponseEvent.toString());
 
        } catch (Exception e) {
 
            // 메시지 발신 : 인터넷응용 등록 보상처리 응답 (실패)
            ContResponseEvent contResponseEvent = ContResponseEvent.builder()
                                                    .globalNo(event.getGlobalNo())
                                                    .eventName("inetaplcaContCreateCompensate")
                                                    .successYn("N")
                                                    .saPponContNo(event.getSaPponContNo())
                                                    .build();
            contEventProducer.contResponse(contResponseEvent);
            log.info("[ICISTR:{}] === [InetaplcaContEventSagaService.inetaplcaContCreateCompensate] === 메시지 발신 : 인터넷응용 등록 보상처리 응답 (실패) === ContResponseEvent : {}",event.getGlobalNo(),contResponseEvent.toString());
            
        }
        
    }
 
    /**
     * <pre>
     * [SAGA-Event] 인터넷응용 계약 수정 보상처리 트랜잭션
     * </pre>
     *
     * @param InetaplcaContCompensateEvent event
     * @return void
     */
    public void inetaplcaContUpdateCompensate(InetaplcaContCompensateEvent event) throws Exception {
 
        log.info("[ICISTR:{}] === [InetaplcaContEventSagaService.inetaplcaContUpdateCompensate] === 인터넷응용 계약 수정 보상처리 트랜잭션 === InetaplcaContUpdateEvent : {}",event.getGlobalNo(),event.toString());
 
        try {
            // 인터넷응용 계약 수정 보상처리
            List<InetaplcaContDto> inetaplcaContDto = queryInetaplcaContService.retvById(event.getSaInetaplcaContNo());
            List<InetaplcaContHstDto> inetaplcaContHstList = queryInetaplcaContService.retvHstById(inetaplcaContDto.get(0).getPrevSaInetaplcaContHstNo());
            InetaplcaContDomain inetaplcacontDomain = InetaplcaContDomain.of(inetaplcaContHstList.get(0));
            inetaplcacontDomain.setTransacNo(inetaplcaContDto.get(0).getTransacNo());
            inetaplcacontDomain.setTransacSttus("CANCEL");
            commandInetaplcaContRepository.save(inetaplcacontDomain);
            log.info("[ICISTR:{}] === [InetaplcaContEventSagaService.inetaplcaContUpdateCompensate] === 인터넷응용 계약 수정 (보상처리) === saInetaplcaContNo : {}",event.getGlobalNo(),event.getSaInetaplcaContNo());
 
            // DB 업데이트가 정상 처리되면 cache에 저장된 키 데이터를 삭제한다
            String key = Constant.INETAPLCA_CONT_KEY + event.getSaInetaplcaContNo();
            redisOperator.delete(key);
            log.info("[ICISTR:{}] === [InetaplcaContEventSagaService.inetaplcaContUpdateCompensate] === 캐시 삭제 key : {}",event.getGlobalNo(),key);
 
            // 메시지 발신 : 인터넷응용 수정 보상처리 응답 (성공)
            ContResponseEvent contResponseEvent = ContResponseEvent.builder()
                                                    .globalNo(event.getGlobalNo())
                                                    .eventName("inetaplcaContUpdateCompensate")
                                                    .successYn("Y")
                                                    .saPponContNo(event.getSaPponContNo())
                                                    .saInetaplcaContNo(event.getSaInetaplcaContNo())
                                                    .build();
            contEventProducer.contResponse(contResponseEvent);
            log.info("[ICISTR:{}] === [InetaplcaContEventSagaService.inetaplcaContUpdateCompensate] === 메시지 발신 : 인터넷응용 수정 보상처리 응답 (성공) === ContResponseEvent : {}",event.getGlobalNo(),contResponseEvent.toString());
 
        } catch (Exception e) {
 
            // 메시지 발신 : 인터넷응용 수정 보상처리 응답 (실패)
            ContResponseEvent contResponseEvent = ContResponseEvent.builder()
                                                    .globalNo(event.getGlobalNo())
                                                    .eventName("inetaplcaContUpdateCompensate")
                                                    .successYn("N")
                                                    .saPponContNo(event.getSaPponContNo())
                                                    .saInetaplcaContNo(event.getSaInetaplcaContNo())
                                                    .build();
            contEventProducer.contResponse(contResponseEvent);
            log.info("[ICISTR:{}] === [InetaplcaContEventSagaService.inetaplcaContUpdateCompensate] === 메시지 발신 : 인터넷응용 수정 보상처리 응답 (실패) === ContResponseEvent : {}",event.getGlobalNo(),contResponseEvent.toString());
            
        }
    }
 
 
       // ...
 
 
}
```

##### (Kafka:발신) ContEventProducer.java

·     

o   

§  

§ Kafka 메시지 수신을 위한 Event를 처리한다.

**ContEventProducer.java** 

```
// ...
@Service
@Slf4j
@RequiredArgsConstructor
public class ContEventProducer {
 
       private final StreamBridge streamBridge;
 
    /**
        * <pre>
        * contResponse 이벤트 발신
        * </pre>
        *
        * @param ContResponseEvent event
        * @return ResultMessage
        */
    public ResultMessage contResponse(ContResponseEvent event){
                
        boolean result = streamBridge.send("contResponse-out-0", event);
              log.info("[ICISTR:{}] === [ContEventProducer.contResponse] === [Producer] === 이벤트 발신 === ContResponseEvent: {}, result : {}",event.getGlobalNo(),event,result);
              
        return getReturnResultMessage(result);
 
    }
 
    // ...
 
}
```

 

 

 

#### MS3 (samp.intelnet)

·     

o   

§ 프로젝트 구조는 다음과 같이 구성된다.

```
§  D:.
§  ├─.mvn
§  ├─.settings
§  ├─.vscode
§  ├─bin
§  ├─gitops
§  ├─k8s
§  ├─lib
§  ├─logs
§  ├─src
§  │  ├─main
§  │  │  ├─java
§  │  │  │  ├─com
§  │  │  │  │  └─kt
§  │  │  │  │      └─icis
§  │  │  │  │          ├─cmmn
§  │  │  │  │          ├─cmmnfrwk
§  │  │  │  │          └─samp
§  │  │  │  │              └─inetaplca
§  │  │  │  │                  ├─cont
§  │  │  │  │                  │  ├─command
§  │  │  │  │                  │  └─query
§  │  │  │  │                  └─pubsub
§  │  │  │  │                      ├─event               # SAGA Kafka event 객체
§  │  │  │  │                      ├─service             # SAGA 서비스 구현
§  │  │  │  │                      └─stream
§  │  │  │  │                          └─function        # SAGA Kafka Producer, Consumer
§  │  │  │  └─org
§  │  │  └─resources
§  │  └─test
└─target
```

 

##### (Kafka:수신) ContEventConsumer.java

·     

o   

§  

§ Kafka 메시지 수신을 위한 Event를 처리한다.

**ContEventConsumer.java** 

```
// ...
@Configuration
@Slf4j
@RequiredArgsConstructor
public class ContEventConsumer {
 
    private Jackson2JsonObjectMapper mapper = new Jackson2JsonObjectMapper();
       private final IntelnetContEventSagaService intelnetContEventSagaService;
 
    @Bean
    public Consumer<Message<String>> intelnetContCreate() {
        return ((msg) -> {
            try {
                IntelnetContCreateEvent event = mapper.fromJson(msg.getPayload(), IntelnetContCreateEvent.class);
                log.info("[ICISTR:{}] === [ContEventConsumer.intelnetContCreate] === [Consumer] 이벤트 수신 === IntelnetContCreateEvent : {}",event.getGlobalNo(),event.toString());
                
                intelnetContEventSagaService.intelnetContCreate(event);
 
                Acknowledgment acknowledgment = msg.getHeaders().get(KafkaHeaders.ACKNOWLEDGMENT, Acknowledgment.class);           
                if(acknowledgment != null){
                    log.info("[ICISTR:{}] === [ContEventConsumer.intelnetContCreate] === [Consumer] Acknowledgment provided === ",event.getGlobalNo());
                    acknowledgment.acknowledge();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
 
    @Bean
    @Transactional
    public Consumer<Message<String>> intelnetContUpdate() {
        return ((msg) -> {
            try {
                IntelnetContUpdateEvent event = mapper.fromJson(msg.getPayload(), IntelnetContUpdateEvent.class);
                log.info("[ICISTR:{}] === [ContEventConsumer.intelnetContUpdate] === [Consumer] 이벤트 수신 === IntelnetContUpdateEvent : {}",event.getGlobalNo(),event.toString());
                
                intelnetContEventSagaService.intelnetContUpdate(event);
 
                Acknowledgment acknowledgment = msg.getHeaders().get(KafkaHeaders.ACKNOWLEDGMENT, Acknowledgment.class);           
                if(acknowledgment != null){
                    log.info("[ICISTR:{}] === [ContEventConsumer.intelnetContUpdate] === [Consumer] Acknowledgment provided === ",event.getGlobalNo());
                    acknowledgment.acknowledge();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
 
    // ...
 
 
}
```

##### (SagaService) IntelnetContEventSagaService.java

·     

o   

§  

- MS3(samp.intelnet) API와 관련된 SAGA 트랜잭션        비즈니스 로직을 작성한다.

§ 정상 트랜잭션 및 취소 트랜잭션 로직을 확인할 수 있다.

**IntelnetContEventSagaService.java** 

```
// ...
@Component
@Slf4j
@RequiredArgsConstructor
public class IntelnetContEventSagaService {
 
       // ...
 
       
    /**
     * <pre>
     * [SAGA-Event] 지능망 계약 등록 트랜잭션
     * </pre>
     *
     * @param IntelnetContCreateEvent event
     * @return void
     */
       public void intelnetContCreate(IntelnetContCreateEvent event) {
 
        log.info("[ICISTR:{}] === [IntelnetContEventSagaService.intelnetContCreate] === 지능망 계약 등록 트랜잭션 === IntelnetContCreateEvent : {}",event.getGlobalNo(),event.toString());
 
        try {
            // 보상 시나리오 : 실패 메시지 발생
            if("Y".equals(event.getCompensateYn())){
                throw new Exception();
            }
 
            // 지능망 계약 등록
            IntelnetContDomain intelnetDomain = new IntelnetContDomain();
            intelnetDomain.setNew(true);
            int saIntelnetContNo = commandIntelnetContRepository.nextVal();
            intelnetDomain.setSaIntelnetContNo(saIntelnetContNo);
            intelnetDomain.setSaPponContNo(event.getSaPponContNo());
            intelnetDomain.setContr(event.getContr());
            intelnetDomain.setPayr(event.getPayr());
            intelnetDomain.setSvcNo(event.getSvcNo());
            intelnetDomain.setCardNo(event.getCardNo());
            intelnetDomain.setDelYn("N");
            intelnetDomain.setTransacNo(event.getGlobalNo());
            intelnetDomain.setTransacSttus("END");
            commandIntelnetContRepository.save(intelnetDomain);
            log.info("[ICISTR:{}] === [IntelnetContEventSagaService.intelnetContCreate] === 지능망 계약 등록",event.getGlobalNo());
            
            // 메시지 발신 : 지능망 등록 응답 (성공)
            ContResponseEvent contResponseEvent = ContResponseEvent.builder()
                                                .globalNo(event.getGlobalNo())
                                                .eventName("intelnetContCreate")
                                                .successYn("Y")
                                                .saPponContNo(event.getSaPponContNo())
                                                .saIntelnetContNo(saIntelnetContNo)
                                                .contr(event.getContr())
                                                .payr(event.getPayr())
                                                .svcNo(event.getSvcNo())
                                                .cardNo(event.getCardNo())
                                                .build();
            contEventProducer.contResponse(contResponseEvent);
            log.info("[ICISTR:{}] === [IntelnetContEventSagaService.intelnetContCreate] === 메시지 발신 : 지능망 등록 응답 (성공) === ContResponseEvent : {}",event.getGlobalNo(),contResponseEvent.toString());
 
        } catch (Exception e) {
 
            // 메시지 발신 : 지능망 등록 응답 (실패)
            ContResponseEvent contResponseEvent = ContResponseEvent.builder()
                                                .globalNo(event.getGlobalNo())
                                                .eventName("intelnetContCreate")
                                                .successYn("N")
                                                .saPponContNo(event.getSaPponContNo())
                                                .contr(event.getContr())
                                                .payr(event.getPayr())
                                                .svcNo(event.getSvcNo())
                                                .cardNo(event.getCardNo())
                                                .build();
            log.info("[ICISTR:{}] === [IntelnetContEventSagaService.intelnetContCreate] === 메시지 발신 : 지능망 등록 응답 (실패) === ContResponseEvent : {}",event.getGlobalNo(),contResponseEvent.toString());
            contEventProducer.contResponse(contResponseEvent);
        }
 
    }
 
    /**
     * <pre>
     * [SAGA-Event] 지능망 계약 수정 트랜잭션
     * </pre>
     *
     * @param IntelnetContUpdateEvent event
     * @return void
     */
    public void intelnetContUpdate(IntelnetContUpdateEvent event) throws Exception {
 
        log.info("[ICISTR:{}] === [IntelnetContEventSagaService.intelnetContUpdate] === 지능망 계약 수정 트랜잭션 === IntelnetContUpdateEvent : {}",event.getGlobalNo(),event.toString());
 
        try {
            // 보상 시나리오 : 실패 메시지 발생
            if("Y".equals(event.getCompensateYn())){
                throw new Exception();
            }
 
            // 지능망 계약 이력 등록
            log.info("[ICISTR:{}] === [IntelnetContEventSagaService.intelnetContUpdate] === 지능망 계약 이력 등록",event.getGlobalNo());
            List<IntelnetContDto> intelnetContDto = queryIntelnetContRepository.retvById(event.getSaIntelnetContNo());
            IntelnetContHstDomain intelnetContHstDomain = IntelnetContHstDomain.of(intelnetContDto.get(0));
            intelnetContHstDomain.setNew(true);
            int saPpontContHstNo = commandIntelnetContHstRepository.nextVal();
            intelnetContHstDomain.setSaIntelnetContHstNo(saPpontContHstNo);
            commandIntelnetContHstRepository.save(intelnetContHstDomain);
 
            // 지능망 계약 수정
            IntelnetContDomain intelnetDomain = new IntelnetContDomain();
            intelnetDomain.setSaIntelnetContNo(event.getSaIntelnetContNo());
            intelnetDomain.setSaPponContNo(event.getSaPponContNo());
            intelnetDomain.setContr(event.getContr());
            intelnetDomain.setPayr(event.getPayr());
            intelnetDomain.setSvcNo(event.getSvcNo());
            intelnetDomain.setCardNo(event.getCardNo());
            intelnetDomain.setDelYn("N");
            intelnetDomain.setTransacNo(event.getGlobalNo());
            intelnetDomain.setTransacSttus("END");
            IntelnetContDomain intelnetContDomain = commandIntelnetContRepository.save(intelnetDomain);
            log.info("[ICISTR:{}] === [IntelnetContEventSagaService.intelnetContUpdate] === 지능망 계약 수정",event.getGlobalNo());
 
            if (intelnetContDomain != null) {
                // DB 업데이트가 정상 처리되면 cache에 저장된 키 데이터를 삭제한다
                String key = Constant.INTELNET_CONT_KEY + event.getSaIntelnetContNo();
                log.info("[ICISTR:{}] === [IntelnetContEventSagaService.intelnetContUpdate] === 캐시 삭제 === key : {}",event.getGlobalNo(),key);
                redisOperator.delete(key);
            }
 
            // 메시지 발신 : 지능망 수정 응답 (성공)
            ContResponseEvent contResponseEvent = ContResponseEvent.builder()
                                                    .globalNo(event.getGlobalNo())
                                                    .eventName("intelnetContUpdate")
                                                    .successYn("Y")
                                                    .saPponContNo(event.getSaPponContNo())
                                                    .saIntelnetContNo(event.getSaIntelnetContNo())
                                                    .contr(event.getContr())
                                                    .payr(event.getPayr())
                                                    .svcNo(event.getSvcNo())
                                                    .cardNo(event.getCardNo())
                                                    .build();
            log.info("[ICISTR:{}] === [IntelnetContEventSagaService.intelnetContUpdate] === 메시지 발신 : 인터넷응용 수정 응답 (성공) === ContResponseEvent : {}",event.getGlobalNo(),contResponseEvent.toString());
            contEventProducer.contResponse(contResponseEvent);
 
        } catch (Exception e) {
 
            // 메시지 발신 : 지능망 수정 응답 (실패)
            ContResponseEvent contResponseEvent = ContResponseEvent.builder()
                                                    .globalNo(event.getGlobalNo())
                                                    .eventName("intelnetContUpdate")
                                                    .successYn("N")
                                                    .saPponContNo(event.getSaPponContNo())
                                                    .saIntelnetContNo(event.getSaIntelnetContNo())
                                                    .contr(event.getContr())
                                                    .payr(event.getPayr())
                                                    .svcNo(event.getSvcNo())
                                                    .cardNo(event.getCardNo())
                                                    .build();
            log.info("[ICISTR:{}] === [IntelnetContEventSagaService.intelnetContUpdate] === 메시지 발신 : 인터넷응용 수정 응답 (실패) === ContResponseEvent : {}",event.getGlobalNo(),contResponseEvent.toString());
            contEventProducer.contResponse(contResponseEvent);
        }
    }
 
 
       // ...
 
 
}
```

 

##### (Kafka:발신) ContEventProducer.java

·     

o   

§  

§ Kafka 메시지 발신을 위한 Event를 처리한다.

**ContEventProducer.java** 

```
// ...
 
@Component
@Slf4j
@RequiredArgsConstructor
public class ContEventProducer {
 
       private final StreamBridge streamBridge;
 
    /**
        * <pre>
        * contResponse 이벤트 발신
        * </pre>
        *
        * @param ContResponseEvent event
        * @return ResultMessage
        */
    public ResultMessage contResponse(ContResponseEvent event){
                
        boolean result = streamBridge.send("contResponse-out-0", event);
              log.info("[ICISTR:{}] === [ContEventProducer.contResponse] === [Producer] === 이벤트 발신 === ContResponseEvent: {}, result : {}",event.getGlobalNo(),event,result);
              
        return getReturnResultMessage(result);
 
    }
 
    // ...
 
}
```

 

 

-----



 

# 6.5 CQRS

### 6.5.1 CQRS 패턴

#### Problem

서비스당 데이터베이스를 갖는 구조로 구현을 하고 나면, 여러 서비스의 공동 데이터를 필요로 하는 하는 쿼리가 요구될 수 있지만, 이것은 불가능하다.

어떻게 마이크로서비스 아키텍처에서 쿼리들을 구현할 수 있을까?

#### Solution

- CQRS는     어플리케이션을 커맨드, 쿼리 두개의 파트로 분할하는 것을 제안한다.
- 커맨드(Command)     파트는 생성, 업데이트, 삭제 요청을     담당한다
- 쿼리(Query) 파트는 Materialized view를 사용하여 쿼리 부분을 처리한다.
- 이벤트 소싱 패턴이 주로 데이터 모든 변경에 대한     이벤트를 만들때 CQRS와 함께 사용된다.
- Materialized     view는 이벤트 스트림의 구독을 통해 갱신된 상태로 유지한다.

#### Command, Query는 왜 분리하는가?

- 기존 아키텍처에서는 동일한 데이터 모델을 사용하여     데이터베이스를 쿼리하고 업데이트 한다 이는 간단하고 기본 CRUD 작업에 적합하다.
- 그러나 더 복잡한 응용 프로그램에서는 이 접근 방식이     다루기 어려울 수 있다. 예를 들어, 읽기 측에서     애플리케이션은 다양한 쿼리를 수행하여 다양한 형태의 데이터 전송 객체(DTO)를 반환할 수     있다. 개체 매핑이 복잡해질 수 있다.
- 쓰기 측면에서 모델은 복잡한 유효성 검사 및 비즈니스     논리를 구현할 수 있다.
- 결과적으로 너무 많은 작업을 수행하는 지나치게 복잡한     모델이 될 수 있다.
- 읽기 및 쓰기 워크로드는 성능 및 확장 요구 사항이     매우 다른 비대칭인 경우가 많다.
- 기존 접근 방식은 데이터 저장소 및 데이터 액세스     계층의 부하와 정보 검색에 필요한 쿼리의 복잡성으로 인해 성능에 부정적인 영향을 미칠 수 있다
- 각 엔터티는 읽기 및 쓰기 작업의 대상이 되기 때문에     보안 및 권한 관리가 복잡해질 수 있으며, 이로 인해 잘못된 프로젝트에서 데이터가 노출될 수     있다

##### CQRS의 이점

- 독립적인 확장 .     CQRS를 사용하면 읽기 및 쓰기 워크로드를 독립적으로 확장할 수 있으며 잠금 경합이 줄어들 수 있다
- 최적화된 데이터 스키마 . 읽기 쪽은 쿼리에 최적화된 스키마를 사용할 수 있고 쓰기 쪽은 업데이트에 최적화된 스키마를 사용할     수 있다
- 보안 . 올바른     도메인 엔터티만 데이터에 대한 쓰기를 수행하고 있는지 확인하는 것이 더 쉽다
- 우려의 분리 . 읽기     쪽과 쓰기 쪽을 분리하면 유지 관리가 더 쉽고 유연한 모델이 될 수 있다. 복잡한 비즈니스     로직의 대부분은 쓰기 모델로 들어간다. 읽기 모델은 비교적 간단할 수 있다
- 더 간단한 쿼리 .     구체화된 뷰를 읽기 데이터베이스에 저장함으로써 애플리케이션은 쿼리할 때 복잡한 조인을 피할 수 있다

![img](file:///C:/Users/kirobo/AppData/Local/Temp/msohtmlclip1/01/clip_image002.png)

  [그림] 명령 모델과 조회 모델이 분리된 모습. (출처 : https://martinfowler.com/bliki/CQRS.html)

 

#### CQRS 패턴을 사용하는 경우

·     많은 사용자가 동일한 데이터에 병렬로 액세스하는 협업 도메인. CQRS를 사용하면 도메인 수준에서 병합 충돌을 최소화하기에 충분한 세분성으로 명령을 정의할 수 있으며, 발생하는 충돌은 명령으로 병합할 수 있다

·     사용자가 일련의 단계로 또는 복잡한 도메인 모델로 복잡한 프로세스를 안내하는 작업 기반 사용자 인터페이스. 쓰기 모델에는 비즈니스 논리, 입력 유효성 검사 및 비즈니스 유효성 검사가 포함된 전체 명령 처리 스택이 있습니다. 쓰기 모델은 관련 개체 집합을 데이터 변경(DDD 용어로 집계)에 대한 단일 단위로 취급하고 이러한 개체가 항상 일관된 상태에 있도록 할 수 있다. 읽기 모델에는 비즈니스 논리나 유효성 검사 스택이 없으며 보기 모델에서 사용할 DTO만 반환합니다. 읽기 모델은 결국 쓰기 모델과 일치한다.

·     특히 읽기 수가 쓰기 수보다 훨씬 많은 경우 데이터 읽기 성능을 데이터 쓰기 성능과 별도로 미세 조정해야 하는 시나리오. 이 시나리오에서는 읽기 모델을 확장할 수 있지만 몇 개의 인스턴스에서만 쓰기 모델을 실행할 수 있다. 적은 수의 쓰기 모델 인스턴스도 병합 충돌의 발생을 최소화하는 데 도움이 된다

·     한 개발자 팀은 쓰기 모델의 일부인 복잡한 도메인 모델에 집중하고 다른 팀은 읽기 모델 및 사용자 인터페이스에 집중할 수 있는 시나리오 이다

·     시스템이 시간이 지남에 따라 발전할 것으로 예상되고 여러 버전의 모델을 포함할 수 있는 시나리오 또는 비즈니스 규칙이 정기적으로 변경되는 시나리오 이다

·     다른 시스템과의 통합, 특히 이벤트 소싱과 결합하여 한 하위 시스템의 일시적인 오류가 다른 하위 시스템의 가용성에 영향을 미치지 않아야 한다

#### CQRS 패턴의 진화

![img](file:///C:/Users/kirobo/AppData/Local/Temp/msohtmlclip1/01/clip_image004.png)

   [그림]기존 아키텍쳐에서 점차 CQRS 패턴이 구현되는 모습 (출처 : http://auconsil.blogspot.com/2013/08/cqrs-command-query-responsibility.html)

- CQRS의     패턴은 좀더 시스템 환경에 따라 DB Repository도 분리하여 진화하고 있다
- 이런 진화의 전제는     MSA기반의 아키텍처 설계및 Database per Service 기반환경으로     분리된 환경에서 적합하다
- Command용 Query용 DB 분리는     CQRS 패턴에서 필수 사항은 아니며 업무특성 및 리소스 관리및 비용을 고려하여 적용해야 한다
- Query용 DB의 NoSQL은 옵션이며 업무 특성상 관계형 DB보다 NoSQL에 효율적인 경우 적용한다

#### CQRS 패턴은 만능인가?

- CQRS 패턴은     다른 패턴들과 마찬가지로 어떠한 문제를 해결하는 하나의 방법이다. 그렇기 때문에 CQRS 패턴은 반드시 적용해야하는 패턴이 아니다.
- 아직 많은 애플리케이션은 CQRS를 적용하지 않는 CRUD 아키텍처가 적합하다.
- 게다가 CQRS 패턴은     쉽게 구현할 수 있는 패턴이 아니기에 불필요한 시스템 복잡도를 야기할 수 있다.

#### CQRS 패턴을 권장하지 않는 경우

- 도메인 또는 비즈니스 규칙이 간단한 경우
- 간단한 CRUD 스타일의     사용자 인터페이스와 데이터 액세스 작업으로 충분한 경우

 

### 6.5.2 ICIS - CQRS 처리 흐름

![img](file:///C:/Users/kirobo/AppData/Local/Temp/msohtmlclip1/01/clip_image006.png)