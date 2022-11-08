# 1.  DDD(domain-driven-design) Pattern



## 1.1. DDD(domain-driven-design) 이란 무엇인가

- DDD (domain-driven-design) 는 특히 복잡하고, 끊임없이 변화하는 비즈니스 규칙이 존재하며, 기업 내에서 해당 비즈니스가 계속해서 유지되고 발전될 것으로 예상되는 시스템을 개발하는 방법론이다.

- DDD 는 핵심 접근법 (The core of the DDD approach) 은 도메인을 분석하고 해당 분석을 토대로 개념적 도메인 모델을 만드는 몇가지 일련의 기술을 사용한다.

- 그리고 만들어진 모델은 개발하려는 소프트웨어의 기반이 된다.

- DDD 방식의 접근법 (분석과 모델링)은 특히 크고 복잡한 도메인을 설계하는데에 적합하다.

- DDD 는 또한 복잡한 시스템을 관리하는데에도 도움이 되며 소프트웨어 개발 프로세스의 다른 측면으로도 해결 방법이 될 수 있다.
  - 도메인에 집중하기도 하면서, DDD 는 비즈니스 팀과 개발 팀이 의사소통 사이에서 오해를 할 수 있는 부분에 집중한다. DDD 가 사용하는 도메인 모델은 상세하고 풍부한 비즈니스 지식을 표현하기도 함과 동시에 실제 작성된 코드와 모델이 비슷해야 하기 때문이다.
  - 도메인 모델은 오랜 기간동안 계속 최신화가 될 수록 유리하다. 가치있는 도메인 지식을 포착함으로써, 그들은 추후에 마주할 시스템 유지보수 업무에도 도움이 될 것이다.
  - DDD 는 거대한 문제를 해결하는 도메인을 효과적으로 나누고 병렬적으로 일을 수행할 수 있게 하며, 비즈니스 가치를 부분적으로 전달할 수도 있다.

- 이러한 DDD 는 오랫동안 유지되고 복잡한 시스템에 적합한데, 만약 당신이 개발하려는 시스템이 작고, 단순하며, 짧은 기간동안에만 유지될 프로젝트라면 DDD 의 장점을 살리지 못할 것이다



## 1.2. DDD(domain-driven-design) 의 컨셉과 용어

- CQRS 패턴을 구현하기 위해서 필요한 몇가지 DDD 개념들을 알아두면 도움이 된다.
  - Domain Model, 도메인 모델
  - Ubiquitous Language, 유비쿼터스 언어
  - Entities, Value Object and services
  - Aggregate and Aggregate Roots



## 1.3. 도메인 모델

- DDD 의 중심에는 domain model 이라는 것이 존재한다.

- 도메인 모엘은 도메인 전문가, 비즈니스 전문가, 소프트웨어 개발자들의 토론과 여러가지의 질문들로 만들어지며 이러한 도메인 모델은 다음과 같은 역할울 수행하게 된다.
  - 도메인 전문가로부터 여러 도메인 지식을을 포착할 수 있다.
  - 팀이 도메인 지식에 대해서 align 할 수 있다.
  - 개발자는 해당 도메인 모델을 토대로 코드를 작성한다.
  - 도메인에 대한 엄청난 변화에도 즉각적으로 반영될 수 있다.

- DDD 는 도메인이 곧 비즈니스 가치이기 떄문에 도메인에 집중한다.

- 이러한 도메인 모델을 통해서 기업은 가치를 구현하고 숨어있는 비즈니스 가치를 찾아낸다.

- DDD 접근 방식의 대부분은 이러한 도메인 모델을 생성, 유지 및 사용하는 방법에 초점을 맞추고 있다.

- 도메인 모델은 일반적으로 Entities, Value Object and Aggregates 와 같은 요소로 구성되며 유비쿼터스 언어의 용어를 사용하여 설명한다.



## 1.4. Ubiquitous Language

- Ubiquitous Language 의 개념은 도메인 모델에 매우 밀접하게 닮아있다.

- 도메인 모델은 도메인 전문가와 개발자 사이에 대한 오해를 줄이고 같은 곳을 바라볼 수 있도록 간극을 좁히는 역할을 수행하기도 한다.

- 만약 도메인 전문가와 개발자가 도메인에 대해서 (앞서 Journey 파트에서 나온 콘토소 컨퍼런스 관리 시스템의 컨퍼런스, 참석자, 의자, 대기자 명단 과 같은) 동일한 용어를 사용한다면 오해에 대한 여지를 줄일 수 있게 된다.

- 좀 더 구체적으로는 모든 사람들이 동일한 개념의 언어를 사용한다면, 언어 간 번역으로 인한 오해가 있을 가능성이 적어진다. 예를 들어서 도메인 전문가가 참석자를 deletegtor 라고 표현하 하는데, 실제로 소프트웨어 개발자는 이를 티켓 판매원이라고 이해할 수 있고 결국 도메인에 대한 공통 개념이 없다 보니 시간이 지날수록 문제가 더 커질 수 있게 되는 것이다.

- 더 구체적으로, 모든 사람이 같은 언어를 사용한다면, 언어 간 번역으로 인한 오해가 있을 가능성이 적다. 예를 들어, 개발자가 "도그니 전문가가 델레 게이트에 대해 이야기한다면, 그는 실제로 소프트웨어의 참석자에 대해 이야기하고 있다"고 생각해야 한다면, 결국 이러한 명확성 부족으로 인해 무언가가 잘못될 것이다.

  

## 1.5. Entities, value objects, and services

- DDD 는 internal artifacts (or building blocks) 를 식별하기 위해서 다음과 같은 용어를 사용한다.

### 1.5.1. Entities

- 엔티티는 식별자를 가지는 객체를 의미한다. 예를 들어서 컨퍼런스 관리 시스템에서 conference 는 엔티티가 될 수 있다.

- conference 의 여러 속성들은 시간이 지남에 따라서 변할 수 있지만 해당 conference 자체는 시스템 안에서 고유할 것이다.

- 이러한 엔티티들은 항상 시스템의 메모리상에만 존재하지 않을 수 있다. 다른 시스템에 잠시 저장할 수도 있으며 DB 를 통해서 영속화 하고 필요할 때 시스템으로 다시 불러올 수도 있다.

### 1.5.2. Value Objects

- 꼭 모든 객체가 유일성을 보장해야 하지 않을 수도 있다.

- 예를 들어서 어떤 객체들은 단지 속성의 값으로만 존재할 수도 있다.

- 예를 들어서 우리의 컨퍼런스 관리 시스템에서 컨퍼런스 참석자의 주소에 대해서는 꼭 식별자를 갖지 않아도 된다.

- 대신 집중하는 것은 value object 는 불변성을 보장해야 한다는 것이다.

### 1.5.3. Services

- 항상 모든것 객체 형태로 관리하지 않아도 된다.

- 예를 들어, 회의 관리 시스템에서는 외부 결제 처리 시스템을 서비스로 모델링하는 것이 합리적일 수 있다.

- 서비스가 필요로 하는 파라미터만 넘기고 결과를 반환받아 특정 기능을 수행할 수도 있다.

- 이러한 서비스의 특징이라고 한다면 엔티티나 값 객체와 달리 stateless 하다는 것이다.

  

## 1.6. Aggregate 와 Aggregate Root

- Entitiy, Value Object 및 Service 가 DDD 가 도메인 모델에서 존재하는 구성요소를 설명하는 데에 사용되는 용어인 반면에 Aggregate 나 Aggregate Root 라는 용어는 특히 그러한 용어들의 그룹화와 life-cycle 에 관련이 있다.

- 만약 공유된 데이터에 대해서 다수의 사용자를 허용하는 시스템을 설계한다면 당신은 consistency 와 usabililty 사이의 트레이드 오프를 잘 파악해야 한다.

- 극단적인 예를 보면, 유저가 어떠한 데이터에 대한 수정을 하고 있을 때, 시스템은 해당 데이터를 다른 사용자가 사용하지 못하도록 system 단에서 lock 을 걸 수 있다.

- 하지만 해당 lock 이 풀릴 떄 까지 시스템의 가용성은 낮아지게 된다.

- 또 다른 극단적인 예를 보자, 만약 다른 사용자가 사용하는 자원에 대해서 lock 을 걸지 않는다면, 다른 사용자들은 어떠한 제약 없이 동시에 해당 데이터를 수정할 수도 있고 시스템의 일관성이 깨지게 될 것이다.

- 이런 상황에서 locking 을 할 것이냐 말 것이냐를 결정하기 위해서는 해당 도메인에 대한 지식이 필요하다.
  - 해당 트랜잭션을 통해서 어떤 값 객체나 엔티티가 영향을 받는지 알아야 한다.
  - 한 오브젝트로 부터 다른 엔티티나 값 객체에 얼마나 영향을 미치며 어디까지 consistency 를 보장하는 경계에 대해서도 인지해야 한다.

### 1.6.1. Aggregate

- DDD 는 일관성을 보장해야 하는 관련된 엔티티와 값 객체들을 하나로 묶는 용어로 aggregate 라는 용어를 사용한다.

- 해당 consistency 경계는 일반적으로 transactional consistency 를 기본으로 한다.

### 1.6.2. Aggregate Root

root entity 로 알려진 Aggregate Root 는 애그리거트에 접근할 수 있는 진입접 (gatekeeper) 을 의미한다.

한 애그리거트에 속해있는 값 객체나 엔티티에 접근하기 위해서는 무조건 Aggregate Root 를 통해서만 수행되어야 한다.

외부 엔티티는 Aggregate Root 대한 레퍼런스만 가질 수 있다.



> #### 요약하자면 aggregate 와 aggregate root 는 DDD 가 일반적으로 도메인 모델에 존재하는 수많은 엔티티와 값 객체 사이에 존재하는 복잡한 관계를 관리하는데 사용되는 메커니즘이다.



## 1.7. Bounded Contexts

- 지금까지 간략히 알아본 DDD 컨셉과 용어는 도메인 모델을 생성, 유지 및 사용하는 것과 관련이 있다.

- 대형 시스템에서는 도메인 모델을 단일로 관리하고 만들어 나가는 것은 실용적이지 않을 수 있다.

- 크기와 복잡성으로 인해서 consistency 역시 보장하고 유지하기가 어렵다.

- 이런 상황에서 DDD는 Bounded Context 에 대한 개념을 사용한다.

- 시스템 내에서 단일 대형 모델이 아닌, 여러 개의 작은 모델을 사용하여 해당 모델들이 적절한 협력을 거쳐서 기능을 수행하게 된다.

- 각각은 전체 시스템 내의 일부의 기능이나 비즈니스 가치에 집중할 수 있게 된다.

- Boundex Context, 바운디드 컨텍스트는 특정 도메인 모델의 문맥 (컨텍스트)이다.

- 각 바운디드 컨텍스트는 자체적인 유비쿼터스 언어를 가지게 될 수 있고 자체적인 개념이 존재할 수도 있다.

[![image](https://user-images.githubusercontent.com/48385288/187064826-4bff4c3f-e8d5-4340-9b69-dea986ab38e1.png)](https://user-images.githubusercontent.com/48385288/187064826-4bff4c3f-e8d5-4340-9b69-dea986ab38e1.png)

- 위 그림은 우리가 journey 에서 구현한 컨퍼런스 관리 시스템이 여러 바운디드 컨텍스트로 분리된 것을 보여준다.

- 실제로는 위 그림에 나온 3개의 바운디드 컨테스트보다 훨씬 많을 것이다.

- 바운디드 컨테스트가 얼마나 커야하고 얼마나 작아야 하는지에 대한 규칙은 없다.

- 궁극적으로 비즈니스에 대한 가치와 요구사항 및 프로젝트 제약에 의해서 결정되는 것이다.



#### 1.7.1. Eric Evans 는 더 거대한 바운디드 컨텍스트에 대한 여러 사례들을 만들었다.

- 거대한 바운디드 컨텍스트
  - 통합 모델로 더 많은 것을 처리할 때 사용자 작업 간의 흐름이 더 부드럽다.
  - 두 개의 별개의 모델과 매핑보다 하나의 일관된 모델을 이해하는 것이 더 쉽다.
  - 두 모델을 해석하는 것은 어렵다. (어쩔 때는 불가능에 가깝다)
  - 두 모델을 사용하는 것은 팀의 커뮤니케이션 비용을 증가시킨다.

- 작은 바운디드 컨텍스트
  - 개발자 간의 커뮤니케이션 오버헤드가 줄어든다.
  - CI, 지속적 통합이 더욱 쉬워진다.
  - 큰 바운디드 컨텍스트는 더욱 추상적이게 될 수 있으며 더 다양한 기술을 요구할 수 있다.



## 1.8. 손상 방지 레이어(Anti-Corruption Layers)

- 서로 다른 바운디드 컨텍스트는 서로 다른 도메인 모델을 가지고 있는다.

- 만약 한 바운디드 컨텍스트에서 다른 바운디드 컨텍스트와 통신할 때, 한 도메인 모델의 특정 개념이 다른 도메인 모델의 개념으로 잘못 침투하는 것을 주의해야 한다.

- 이 때, Anti-Corruption Layers 는 두 도메인 모델 사이를 깨끗하게 만드는 게이트웨이 역할을 수행한다.



## 1.9. Context Maps

- 크고 복잡한 시스템은 다양한 방식으로 서로 상호작용하는 여러 경계 컨텍스트를 가질 수 있다.

- context map 은 각각의 바운디드 컨텍스트 사이의 관계를 설명하는 문서이다.

- 이는 diagram 이 될 수도 있고, 표가 될 수도 있으며 문자가 될 수 있다.

- 컨텍스트맵은 높은 수준에서 시스템을 시각화 하는데에 도움을 주며 바운디드 컨텍스트 사이를 명확히 하는 데 도움이 된다.

- 바운디드 컨텍스트가 데이터를 교환하고 공유하는 위치, 방법 그리고 한 도메인 모델에서 다른 도메인 모델로 이동할 때 데이터를 어디서 변환하는지를 보여준다.

- customer 과 같은 비즈니스 엔티티는 여러 바운디드 컨텍스트에 존재할 수 있다. 하지만 특정 바운디드 컨텍스트와 관련된 다른 속성값을 포함하고 표현할 수 있다.

- customer 엔티티가 한 바운디드 컨텍스트에서 다른 바운디드 컨텍스트로 이동할 때 현재 컨텍스트에 대한 속성을 노출하거나 숨기는 등 변환될 수도 있다.



## 1.10. Bounded Context 와 Multiple Architecture

- 바운디드 컨텍스트는 일반적으로 시스템 내의 다른 바운디드 컨텍스트와 명확하게 경계를 나타낸다. 
- 바운디드 컨텍스트가 DDD 접근법에 따라서 구현된다면, 바운디드 컨텍스트는 자제 도메인 모델과 자체 유비쿼터스 언어를 갖게 된다.

- 바운디드 컨텍스트의 구현은 일반적으로 데이터 저장소에서 UI 에 이르기 까지 모든 것이 포함된다.

- 또한 동일한 도메인 개념이 여러 바운디드 컨텍스트에 존재할 수 있다. 예를 들어서 컨퍼런스 관리 시스템에서 참석자의 개념은 예약을 다루는 다른 바운디드 컨텍스트에서 다른 의미로 사용될 수 있다.

- 각 바운디드 컨텍스트의 도메인 전문가의 관점에서 이러한 다양한 버전의 참석자는 다른 행동과 속성들이 필요할 수 있다.

- 예를 들어서 예약 바운디드 컨텍스트에서 참석자는 예약 및 결제를 하는 사용자를 의미한다. 결국 결제와 관련된 정보를 요구할 수도 있고 호텔 바운디드 컨텍스트에서 참석자는 흠연 선호도와 같은 정보가 중요할 수도 있다.

- 이러한 바운디드 컨텍스트를 보고 알 수 있는 중요한 것은 각각의 서로 다른 바운디드 컨텍스트는 다른 시스템 아키텍처가 적용될 수 있다는 것이다.

- 예를 들어서 하나의 바운디드 컨텍스트는 DDD 의 Layered Architecture 를 이용해서 구현될 수 있고, 다른 바운디드 컨텍스트는 단순 CRUD 아키텍처, 또 다른 바운디드 컨텍스트는 CQRS 패턴을 적용한 아키텍처를 사용할 수 있다는 것이다.

- 아래의 그림은 영속성 자장 장치부터 UI 까지, 포함된 모든 컴포넌트를 보여준다.

[![image](https://user-images.githubusercontent.com/48385288/187066142-712a959c-6052-406f-9060-b8ee16a860e9.png)](https://user-images.githubusercontent.com/48385288/187066142-712a959c-6052-406f-9060-b8ee16a860e9.png)

- 복잡성을 관리하는 것 이외에도, 시스템을 바운디드 컨텍스트로 나누는 또 다른 이점이 있다.

- 다른 요구사항에 따라서 적절한 아키텍처를 선택할 수도 있고, 특정 부분만 다른 기술을 사용할 수도 있다.

- 복잡성을 관리하는 것 외에도, 시스템을 경계된 맥락으로 나누는 또 다른 이점이 있다. 
  - 시스템의 다른 부분에 적절한 기술 아키텍처를 사용하여 각 부분의 특성을 광고할 수 있다. 
  - 예를 들어, 시스템의 복잡한 부분인지, 핵심 도메인 기능이 포함되어 있는지, 예상 수명과 같은 질문을 해결할 수 있습니다.



## 1.11. Bounded Context 와 multiple 개발팀

- 다른 바운디드 컨텍스트를 명확하게 분리하고 별도의 도메인 모델과 유비쿼터스 언어로 작업하면 각 경계 컨텍스트에 대해 별도의 팀을 사용하여 개발 작업을 병렬화할 수 있다.

  

## 1.12. 여러 바운디드 컨텍스트를 maintaining 하기

- 바운디드 컨텍스트는 더 관리하기 쉬운 부분으로 나뉘기 때문에 대규모 시스템의 복잡성을 관리하는데에 도움되지만, 각 바운디드 컨텍스트가 혼자 독립적으로 존재할 가능성은 거의 없다.

- 바운디드 컨텍스트는 서로 데이터를 교환해야 하며, 다른 도메인 모델에서 동일한 도메인 객체를 변환을 해야 하는 경우 이러한 데이터 변경은 꽤나 복잡할 것이다.

- 컨퍼런스 관리 시스템에서는 컨퍼런스 예약, 배지 인쇄 및 호텔 예약 문제를 다루는 경계된 맥락 간에 attendee 객체에 대한 속성을 변환해야 할 수도 있다.

- DDD 접근 방식은 Anti-Corruption Layers 를 사용하거나 [Shared Kernel](https://github.com/dhslrl321/cqrs-journey-guide-korean/blob/master/terms/Shared Kernel.md) 을 사용하는 것과 같은 여러 바운디드 컨텍스트 사이에서 여러 모델 간의 상호 작용을 처리하기 위한 다양한 접근 방식을 제공한다

> Note: 기술적인 관점에서 서로 다른 바운디드 컨텍스트 사이의 통신은 messaging infrastructure 를 이용한 비동기 통신을 주로 사용한다.





# 2. CQRS(Command Query Responsibility Segregation) Pattern



## 2.1. CQRS and DDD

- 이번 챕터의 소개에서 언급했듯이, 몇가지의 DDD 의 개념과 용어를 이해하는 것은 CQRS 패턴을 학습하는 데에 꽤나 유용하다.

- CQRS 패턴을 알린 많은 아이디어는 DDD 실무자들이 real world 에서 DDD 를 적용할 때 직면한 문제에서 비롯되었다.

- 따라서 DDD 접근 방식을 사용하기로 결정한 경우, CQRS 패턴은 시스템 내에 존재하는 일부 바운디드 컨텍스트에서 매우 적합하며, 일부 도메인 모델에서 CQRS 패턴의 구현이 상대적으로 간단하다는 것을 알 수 있다.

- **몇몇의 일부 전문가들은 DDD 접근 방식이 CQRS 패턴을 구현하기 위한 필수적인 전제 조건이라고 생각하기도 한다.**

- 그러나 많은 사람들은 DDD 를 하지 않은 프로젝트에서 CQRS 를 적용하여 실질적인 효과를 본 경험들이 많았으며, **이는 곧 DDD 가 CQRS 의 필수 전제 조건이 아니라는 것을 의미한다.**

- 이번 챕터에서는 이 CQRS Journey 에서 논의된 거의 모든 것의 핵심인 Command Query Responsibility Seg- regation (CQRS) pattern, 명령-조회의 책임 분리 패턴에 대해서 알아본다.

- 여기서 우리는 CQRS 패턴을 적용하는 것이 엔터프라이즈 애플리케이션의 아키텍처에 어떤 영향을 미치는지 알아볼 것이다.

- CQRS는 엔터프라이즈 애플리케이션을 설계하고 구축할 때 발생하는 모든 문제에 대한 은색 총알이 아니라는 것을 이해하는 것이 중요하다.

- 시스템의 모든 부분이 아니라 핵심 부분에 선택적으로 적용하면 CQRS 패턴을 적용하는 모든 이점을 볼 수 있다.

- 2장, CQRS 및 이벤트 소싱 탐색의 "도메인 분해"는 Contoso 컨퍼런스 관리 시스템을 바운디드 컨텍스트로 나누고 CQRS 패턴을 사용하면 어떤 바운디드 컨텍스트를 식별했는지 설명한다.

- CQRS 및 이벤트 소싱 탐색의 후속 장은 실제 애플리케이션을 구현할 때 CQRS 패턴 및 기타 관련 패턴을 적용하는 방법에 대한 여러 방법들을 제공할 것이다.



## 2.2. CQRS 란 무엇인가

- 버트란드 메이어의 "Object Oriented Software Construction" 책에서 [*Command Query Separation](https://github.com/dhslrl321/cqrs-journey-guide-korean/blob/master/terms/Command and Query Separation.md) 이라는 용어가 처음 소개되었다.

- 어떠한 객체의 메서드는 명령(command) 형이거나 조회(query) 형 둘중에 하나라는 개념이다.
  - Command : 상태를 변경하고 데이터를 반환해선 안된다
  - Query : 데이터를 반환하고 상태를 변경하면 안된다

- 이 원칙을 지키면 소프트웨어가 더욱 단순해지고 이해하기 쉬워지며 변경에 용이해진다.



#### 2.3 CQRS 는 이 원칙을 차용한다.

- 이 간단한 패턴에서 중요하고 흥미로운 것은 엔터프라이즈 시스템을 구축할 때 어떻게, 어디서, 왜 사용하는지 이다.

- 이 간단한 패턴을 사용하면 확장성이 용이하며, 복잡성을 쉽게 관리할 수 있고, 시스템의 일부 부분에서 변화하는 비즈니스 요구사항을 관리하는 것과 같은 광범위한 문제를 쉽게 해결할 수 있다.



## 2.4 Read Side and Write Side

- 아래 그림은 CQRS 패턴을 적용한 애플리케이션의 일반적인 처리 흐름을 보여준다.

[![image](https://user-images.githubusercontent.com/48385288/187067484-127d0efd-d0f5-46ae-90a1-b408722632c6.png)](https://user-images.githubusercontent.com/48385288/187067484-127d0efd-d0f5-46ae-90a1-b408722632c6.png)

- 위 그림에서 어떻게 시스템이 read side 와 write side 로 분리되었는지 볼 수 있다.

- Read Side 의 객체들은 오로지 query 메서드만 가지고 있으며 write side 의 객체들은 오로지 command 메서드만 가지고 있다.

- 이렇게 분리를 하는 데에는 몇가지 동기가 존재한다.
  - 많은 비즈니스 시스템에서, 읽기와 쓰기에 대한 불균형이 존재힌다.
    - 시스템은 모든 쓰기와 읽기 작업이 1:1000 비율로 존재한다.
    - 이 둘을 분리하면 쓰기 연산과 읽기 연산을 각각 독립적으로 분리할 수 있다.
    - 읽기 연산과 쓰기 연산에 각각 최적화된 솔루션을 사용할 수 있다.
  - 일반적으로 command 는 시스템이 정확하고 일관된 데이터를 영속화 할 수 있도록 복잡한 비즈니스 로직을 포함한다.
    - 이는 query 보다 훨씬 복잡하기 때문에 이 둘을 분리하면 더 간단하고 유지보수가 쉬운 모델을 만들 수 있다.
  - 앞서 말했든 데이터 저장소 수준에서도 Segregation 을 할 수 있다.
    - command 는 제 3 정규화에 가깝고 데이터 수정에 최적화된 데이터베이스 스키마를 사용할 수 있다.
    - 읽기 측은 빠른 쿼리 작업에 최적화된 비정규화, 반정규화, 역정규화된 데이터베이스 스키마를 사용할 수 있다.

- 그림 1에 표시된 것과 같이 CQRS 패턴을 채택하는 것이 실용성 측면에서 몇 가지 질문이 있다.
  - 읽기 작업과 쓰기 작업을 분리한다면 각각은 복합 모델보다 간단할 수 있지만, 전체 아키텍처는 전통적인 접근 방식보다 훨씬 복잡한데, 우리가 개발할 서비스도 분리할 만큼 복잡한가?
  - 읽기 쪽에 있는 데이터 저장소의 변경 사항 전파를 어떻게 관리해 할까?
  - 쓰기 연산과 읽기 연산 사이의 딜레이는 어떻게 처리할 것인가?

- 이번 장에서는 이러한 질문에 대한 답을 찾아가며 CQRS 패턴을 사용하기 위한 동기를 탐구하기 시작할 것이다.



## 2.5 CQRS 와 domain-driven-design

- 이전 CQRS in Context 챕터에서 CQRS Pattern 을 이해하는데에 도움이 되는 domain-driven-design 에 대한 몇가지 개념들을 소개했다

- 두가지 분야가 특히 중요했다.
  - **첫번째는 모델의 개념이다.**
    - 에릭 에반스는 그의 책 “Domain-Driven Design: Tackling Complexity in the Heart of Software,” (Addison-Wesley Professional, 2003) 는 효과적인 모델링을 위해서 다음과 같은 것들을 제공한다.
      - Models should be bound to the implementation.
      - You should cultivate a language based on the model.
      - Models should be knowledge rich.
      - you should brainstorm and experiment to develop the model.
    - 위 목록은 모델의 아이디어를 포착하는 데 도움이 되지만, 개념을 더 깊이 이해하기 위해 책을 읽는 것을 대신할 수는 없습니다
      - 아래 그림에서 도메인 모델의 구현은 write side 에 존재하고 시스템의 복잡한 비즈니스 로직을 캡슐화 한다.
      - 읽기 측면은 쿼리를 통해 액세스하는 시스템 상태의 더 간단하게 구성되며, 읽기 연산에 최적화된 형태로 구성된다.

- [![image](https://user-images.githubusercontent.com/48385288/187067484-127d0efd-d0f5-46ae-90a1-b408722632c6.png)](https://user-images.githubusercontent.com/48385288/187067484-127d0efd-d0f5-46ae-90a1-b408722632c6.png)
  - **두 번째로 중요한 개념은 DDD가 크고 복잡한 시스템을 바운디드 컨텍스트로 알려진 더 관리하기 쉬운 단위로 나누는 방식이다**.
    - 바운디드 컨텍스트는 모델의 컨텍스트를 정의한다.
    - 시스템의 일부에 CQRS 패턴을 적용해야 한다고 말할 때, 바운디드 컨텍스트 내에서 CQRS 패턴을 구현해야 한다는 것을 의미한다
    - 도메인 모델에서 컨텍스트의 경계를 구분짓는게 반드시 CQRS 패턴을 사용해야 하는 이유는 아니다.
    - DDD 에서 바운디드 컨텍스트는 유비쿼터스 언어의 범위에 맞는 모델의 문맥을 정의한다.
  - CQRS 패턴은 단지 시스템을 구현하는 구현상에서 확장성이나 단순성 혹은 유지보수성에 대한 이점을 얻기 위함이다.
  - 이러한 차이점들 때문에, 바운디드 컨텍스트가 아니라 비즈니스 컴포넌트에 CQRS 패턴을 적용하는 것이 더욱 합리적이게 보이기도 한다.
  - 비즈니스 컴포넌트는 바운디드 컨텍스트 맵과 일치할 확률이 높다.

> #### 요약하자면, CQRS 패턴을 아키텍처 전반에 걸쳐서 사용해야할 필요는 없다. 독립적으로 설계하고 구현할 수 있는 시스템에서 컨텍스트의 경계를 명확하게 구분지을 수 있고 명확한 비즈니스 이점이 있는 부분에만 CQRS 패턴을 적용해야 한다
>



## 2.6. Commands, Events 그리고 Messages 의 등장

- DDD 는 모델을 사용하게 하고 유비쿼터스 언어를 통해서 비즈니스와 개발 사이의 간극을 줄이도록 해주어 도메인에 대한 common 한 지식을 쌓게 하여 분석하고 설계하는 과정이다.

- 필요에 따라서 DDD 접근법은 단지 비즈니스 도메인의 데이터가 아니라 행동을 분석하는 것을 더 지향하며, 이는 동작하는 소프트웨어를 모델링하고 구현하는 데에 중점을 둔다.

- 코드에서 도메인 모델을 구현하는 자연스러운 방법은 command 와 event 를 사용하는 것이다.

- 이것은 특히 UI 를 사용하는 애플리케이션에 더욱 유리하다.

> DDD는 명령과 이벤트를 사용하여 구현된 도메인 모델에 지정된 작업과 동작을 보는 것이 일반적인 유일한 접근 방식은 아니다. 그러나 CQRS 패턴의 많은 옹호자들은 DDD 접근법의 영향을 강하게 받았기 때문에 CQRS 패턴에 대한 논의가 있을 때마다 DDD 용어가 사용되는 것을 보는 것이 일반적이다.

### 2.6.1. Command

- 명령은 필수적이다; 그것들은 시스템이 작업이나 행동을 형성하도록 요청이다. 
- 예를 들어, "컨퍼런스 X를 위한 두 장소를 예약하세요" 또는 "연사 Y를 Z호실에 할당하세요." 명령은 보통 한 명의 수신자에 의해 한 번만 처리됩니다.

### 2.6.2. Events

- 이벤트는 알림이다. 이미 다른 이해 관계자들에게 과거의 사건을 보고한다.

- 예를 들어, "고객의 신용 카드에 200달러가 청구되었습니다" 또는 "컨퍼런스 X를 위해 10개의 좌석이 예약되었습니다." 와 같다.

- 하나의 이벤트는 여러 소비자에 의해 여러 번 처리될 수 있다.
  - 명령과 이벤트 모두 객체 간에 데이터를 교환하는 데 사용되는 메시지 유형이다.

- DDD 용어로, 이러한 메시지는 비즈니스 행동을 나타내고 시스템이 메시지 뒤에 있는 비즈니스 의도를 포착하는 데 도움을 준다.

- CQRS 패턴을 구현할 때, 읽기 작업과 쓰기 작업에 대해 별도의 데이터 저장소를 사용한다.

- 각 데이터 저장소는 지원하는 사용 사례에 최적화되어 있다.

[![image](https://user-images.githubusercontent.com/48385288/187082278-c0f03223-3e75-45f0-8c94-ab39cb4ed663.png)](https://user-images.githubusercontent.com/48385288/187082278-c0f03223-3e75-45f0-8c94-ab39cb4ed663.png)

- 위의 그림은 CQRS 패턴을 구현할 때 명령과 이벤트를 사용하는 방법을 보여준다.

- Event 는 command side (처리 명령의 결과)의 변경 사항을 query side 로 동기화하는 메커니즘의 기초를 제공한다.

- command side 가 애플리케이션의 상태가 변경될 때마다 이벤트를 발생시키면, query side 는 해당 이벤트에 응답하고 쿼리와 뷰에서 사용되는 데이터를 업데이트해야 한다.

> #### 우리는 커맨드와 이벤트를 핸들링 할 수 있는 infrastructure 가 필요하다. 그리고 이에 대해서는 다음 챕터에서 자세히 알아볼 것이다.
>
> #### *** 이벤트가 write side 와 read side 를 동기화하는 유일한 방법만은 아니다.



## 2.7. 왜 CQRS 를 사용해야 할까

- 잠시 CQRS 뒤로 물러서서 바라보자.

- DDD 에서 바운디드 컨텍스트를 나누는 것에 대한 이점은 명확하다.

- 바운디드 컨텍스트로 나누면서 복잡하며 계속해서 변화하는 비즈니스 요구사항에 따라서 해당 문맥에 대해서 집중하고 구분할 수 있게 된다.

- 이 책에서 CQRS 를 배웠기 때문이 아니라 확실히 비즈니스 이점을 제공하는 경우에만 특정 바운디드 컨텍스트 내에서 CQRS 패턴을 적용하는 것을 고려해야 한다.

- CQRS 패턴을 적용함으로써 얻을 수 있는 가장 일반적인 비즈니스 이점은 **확장성**, 도메인의 복잡한 **측면의 단순화**, **유연성 향상, 변화하는 비즈니스 요구 사항에 대한 수용 가능성**이다.

  

### 2.7.1. Scalability, 확장성

- 많은 enterprise 시스템에서, read 연산이 write 연산보다 훨씬 많이 일어나기 때문에 당신의 scalability, 즉 서버의 확장성은 read side 와 write side 각각에 다른 기준이 적용되어야 한다.

- 하나의 Bounded Context 내에서 read side 와 write side 를 분리함으로써 각각을 서로 다르게 확장할 수 있음을 의미한다.

- 예를 들어, Windows Azure에서 애플리케이션을 호스팅하는 경우, 각 side 를 분리한다면 서로 다른 수의 인스턴스를 추가하여 독립적으로 확장할 수 있다.



### 2.7.2 Reduced Complexity, 복잡성 줄이기

- 당신의 복잡한 도메인 속에서, 하나의 객체 안에 읽기 연산과 쓰기 연산을 모두 설계하고 구현하는 것은 복잡성을 더욱 악화시킬 수 있다.

- 여러 케이스에서, 비즈니스 로직의 복잡성은 update 와 트랜잭셔널한 연산을 수행할 떄에만 발생하지만, 반대로 읽기 연산은 그보다 훨씬 단순하다.

- 비즈니스 로직과 읽기 연산이 하나의 모델에 뒤섞여 있다면, 더욱 어려운 비즈니스적 문제를 해결하거나, 대용량 처리, 분산 처리, 성능, 트랜잭션 및 consistency 를 처리하는 데에 큰 산이 될 것이다.

- 읽기 연산과 비즈니스 로직을 분리하면 이러한 문제를 해결하는데에 도움이 되지만 많은 대다수는 기존 모델을 분리하고 이해하는데에 여러 노력이 필요할 수도 있다.

- 많은 다른 Pattern 들 처럼, CQRS 패턴을 도메인에 내제된 복잡성 중 일부를 더 쉽게 이해되고 특정 영역에서 문제 해결에 집중할 수 있도록 하는 접근 방식 또는 메커니즘으로 바라볼 수 있다.

- 읽기 연산과 비즈니스 로직을 분리하여 경계 컨텍스트를 단순화하는 또 다른 잠재적인 이점은 테스트를 더 쉽게 만들 수 있다는 것이다.

> Separation of concerns is the key motivation behind Bertrand Meyer’s Command Query Separation Principle: “The really valuable idea in this principle is that it’s extremely handy if you can clearly separate methods that change state from those that don’t. This is because you can use queries in many situations with much more confidence, introducing them anywhere, changing their order. You have to be more careful with modifiers.” —Martin Fowler, Command- QuerySeparation



### 2.7.3. Flexibility, 유연성

- CQRS 패턴을 사용할 때 얻어지는 유연성은 read-side 와 write-side 를 분리할 때 주로 발생된다.

- CQRS 패턴을 사용한다면, UI 에서 보여질 특정 쿼리를 추가한다거나 하는 read-side 에서 변경 및 추가가 쉬워진다.

- 비즈니스 로직에 어떠한 영향을 끼치지 않는다고 확신할 수 있을 때, UI 에서 보여질 특정 view 를 위해서 쿼리를 추가하는 것과 같이 read-side 에서 변경하는 것이 훨씬 쉬워진다.

- write-side 에서, 도메인의 핵심 비즈니스 로직만 표현하는 모델을 갖는다는 것은 read 연산과 write 연산이 혼재되어있을 때 보다 훨씬 간단하다는 것을 의미한다.

- 장기적으로 봤을 때, 당신의 핵심 도메인 비즈니스 로직을 명확하게 설명하는 코어 도메인 모델이 가치 있는 자산이 될 것이다.

- 당신이 직면해있는 계속해서 변경되는 비즈니스 환경과 경쟁의 압박 속에서 훨씬 더 agile 스럽게 해준다.

이러한 유연성과 agility 은 DDD 의 Continousous Integration 과 관련이 있다.

- 어떤 경우에는 write side 와 read side 에 서로 다른 개발 팀을 구성하는 것이 가능할 수도 있지만, 현실에서 이것은 아마도 얼마나 특정 바운디드 컨텍스트가 얼마나 큰지에 달려있을 것이다.

> “Continuous integration means that all work within the context is being merged and made consistent fre- quently enough that when splinters happen they are caught and corrected quickly.” —Eric Evans, “Domain- Driven Design,” p342.



### 2.7.4. 비즈니스에 집중하기

- 만약 당신이 CRUD 를 사용한다면, 기술은 해결책을 형성하는 경향이 있다.

- CQRS 패턴을 채택하는 것은 당신이 비즈니스에 집중하고 task-oridened (작업 지향) UI 를 만드는 데에 집중할 수 있도록 도와준다.

- read side 와 write side 의 관심사를 분리하는 것의 결과는 변화하는 비즈니스 요구사항에 더 잘 적응할 수 있는 솔루션이다. 이로 인해서 장기적으로 개발 및 유지보수 비용이 절감된다.



### 2.7.5. 작업 기반 UI 만들기를 촉진 (Facilitates building task-based UIs)

- CQRS 패턴을 구현할 때, 도메인에게 작업을 시작하도록 할때 command 를 사용한다.

- 이러한 command 들은 일반적으로 도메인의 연산과 [*유비쿼터스 언어](https://github.com/dhslrl321/cqrs-journey-guide-korean/blob/master/terms/Ubiquitous Language.md)에 밀접하게 연관이 있다.

- 예를 들어서 "컨퍼런스 X 의 두 자리 좌석을 예매한다" 라는 command 가 있다고 해보자. 전통적인 CRUD 스타일의 작업을 하는 대신 이러한 명령을 도메인으로 보내기 위한 UI 를 설계할 수 있다.

- 이렇게 된다면 더욱 직관적이게 되고 작업 기반 UI 를 더 쉽게 설계할 수 있게 된다.

  

### 2.7.6. CQRS 패턴을 채택하는데 존재하는 장벽

- 특정 시나리오에서 CQRS 패턴을 채택하는 데 많은 명확한 이점이 존재하는 것은 사실이나, 이러한 혜택이 솔루션의 추가적인 복잡성을 해결하는 데에 도움이 된다는 것을 이해 관계자에게 확신시키는 것이 어려울 수 있다.



### 2.7.7. 언제 CQRS 를 사용해야 할까

- 비록 우리가 왜 당신이 CQRS 패턴을 당신의 시스템의 몇몇 바운디드 컨텍스트에 도입해야 하는가에 대한 몇가지 이유를 앞서 이야기했지만,

- 시스템의 일부 경계 컨텍스트에 CQRS 패턴을 적용하기로 결정한 몇 가지 이유를 설명했지만, 어떻게 CQRS 패턴을 식별할 수 있는지는 설명하지 않았다.

- 이번에는 CQRS 패턴을 적용하면 도움이 될 수 있는 바운디드 컨텍스트를 식별하는 데 도움이 되는 몇 가지 엄지손가락 규칙 (some rule of thumb) 을 이야기해보겠다.

- 일반적으로 CQRS 패턴을 적용하면 collaborative 하고 복잡하며 끊임없이 변화하는 비즈니스 규칙인 바운디드 컨택스트에서 가장 많은 가치를 제공할 수 있으며, 비즈니스에 상당한 경쟁 우위를 점할 수 있다.

- 비즈니스 요구 사항을 분석하고, 유용한 모델을 구축하고, 코드로 표현하고, 이러한 경계 컨텍스트를 위해 CQRS 패턴을 사용하여 구현하는 것은 모두 시간과 비용이 든다.

- 당신은 이 투자가 장기적으로 이점을 가져다 준다는 것을 알아야 한다. 시스템의 적응성과 유연성 향상 또는 유지 보수 비용 절감과 같은 수익을 기대하지 않는다면 이 투자를 할 가치가 없을 것이다.

  

### 2.7.8. Collaborative domains

- Udi Dahan 과 Greg Young 모두 바운디드 컨텍스트에 CQRS 패턴을 적용할 때 발생되는 최고 이점으로 협업을 꼽았다.

- CQRS 패턴은 특히 도움된다. 협업에 (포함된, 복잡한 의사결정 - )

- CQRS 패턴은 협업이 shared data 에서 여러 actor 가 접근할 때 결과가 어떻게 되어야 하는지에 대한 복잡한 결정이 필요할 떄 특히 유용하다.
  - 예를 들어서 "last on win" (마지막에 수행한 연산에 따라 데이터가 반영) 규칙이 비즈니스 로직의 수행 결과로 적용되어야 하는가 아니면 더욱 정교한 기준이 필요한가?
  - actor 가 반드시 사람이 아니라는 점에 유의하는 것이 중요하다. actor 는 데이터에서 독립적으로 작동할 수 있는 시스템의 다른 부분일 수 있다.

> Collaborative 행위는 CQRS 패턴을 적용하는데 좋은 지표다. 하지만 그렇다고 해서 쉽고 빠르게 적용할 수 있다는 것은 아니다

- 대부분의 시스템에서 collaborative 한 부분은 꽤나 복잡하고 자주 변경되며 중요한 bounded context 이다. 그렇다고 collaborative 한 도메인만 CQRS 에 적합한 것 또한 역시 아니다. 몇몇의 collaborative 하지 않은 도메인도 CQRS 에 적합할 수 있다.



### 2.7.9. Stale Data, 오래된 데이터

- collaborative 한 환경에서 다수의 유저가 동일한 데이터에 동시에 명령을 내릴 수 있다. 그렇다면 당신은 stale 한 데이터 문제에 직면하게 된 것이다.

- 만약 어떠한 유저가 데이터를 조회하는 순간 다른 유저가 해당 데이터를 수정했다면 첫번쨰 유저가 보고 있는 데이터는 stale 한 데이터이다.

- 어떤 아키텍처를 선택하더라도 당신은 분명 이러한 문제에 직면하게 된다.

- 이럴 때 당신은 DB 에 특정 locking 을 사용하거나 cache 에 새로운 refresh 정책을 정의하여 이러한 문제를 application 단에서 혹은 solutoin 단에서 해결할 수 있다.

- 앞선 예는 오래된 데이터를 만나고 처리해야 하는 시스템의 두가지 영역을 보여준다. 대부분의 협업 엔터프라이즈 시스템에는 더 많은 문제가 있을 수 있는데, CQRS 패턴은 아키텍처 수준에서 이런 문제들을 해결할 수 있도록 한다.

- 데이터의 변경은 write-side 에서 발생하며 사용자는 read-side 에 쿼리를 통해 데이터를 확인한다.

- write-side 에서 read side 로 변경 사항을 반영 (push) 하는데, 사용한 매커니즘은 write side 의 데이터가 오래 걸릴 떄와 얼마나 유지되는지 제어하는 메커니즘이기도 하다. 이것은 오래된 데이터의 관리가 항상 일관된 방식으로 달워지지 않는 세부 구현에 더 가깝고 다른 아키텍처와는 다른 점이다.

- "CQRS and ES Deep Dive" 챕터에서는 write side 와 read side 간의 동기화 메커니즘이 애플리케이션의 stale data 문제를 관리하는 방법을 어떻게 결정하는지 살펴볼 것이다.



### 2.7.10. Cloud 로 옮겨가기

- 단지 애플리케이션을 클라우드로 이동하거나 클라우드용 애플리케이션을 개발하는 것은 CQRS 패턴을 구현하는 충분한 이유가 아니다.

- 게다가 PaaS(Platform as a Service) 클라우드 컴퓨팅 플랫폼에서 제공되는 많은 여러 서비스는 확상성이 뛰어난 데이터 저장소, 메시징 서비스 및 캐싱 서비스가 CQRS 구현을 위한 인프라로 적합하다.



## 2.8 언제 CQRS 를 사용하지 말아야 할까

- 간단하고 정적이며 비 협업적인 비핵심 바운디드 컨텍스트에는 CQRS 패턴을 적용함으로써 이익을 볼 가능성이 적다.

- 대부분의 시스템에서 여러 바운디드 컨텍스트는 아마도 CQRS 패턴을 사용하면 도움이 되지 않을 것이다.

- CQRS 패턴은 명확한 비즈니스 이점을 식별할 수 있을 경우에만 사용하는 것이 옳다.



## 2.9 요약

- ***CQRS 패턴은 시스템에서 제한된 바운디드 컨텍스트를 구축하기 위한 원동력이다.***

- ***CQRS 패턴을 사용할 위치를 식별하려면 패턴 구현의 초기 비용과 오버헤드, 미래의 비즈니스 이점 사이의 장단점을 명확히 분석해야 한다.***

- ***CQRS 패턴을 적용할 수 있는 곳을 식별하는 데 유용한 지표는 복잡하며 유동적인 비즈니스 규칙을 포함하여 collaborative 한 구성 요소를 찾는 것이다.***





# 3. Event Sourcing

- ES (Event Sourcing) 과 CQRS (Command Query Responsibility Segration) 는 주로 함께 언급되곤 한다.

- ES 가 CQRS 를 의미하지 않고 역시 CQRS 가 ES 를 의미하지는 않지만 각각이 서로 상호보완 관계라는 것을 알게 될 것이다.

- **이벤트 소싱의 이해를 돕기 위해서 Event 라는 것의 특징을 정확히 알고 가는 것이 중요하다**
  - Event 는 과거에 일어난 사건이다.
    - 예를 들면 다음과 같다. "the speaker was booked", "the seat was reserved"
    - 과거 시제를 사용하는 것을 주목하라.
  - Event 는 불변하다
    - 이벤트는 과거에 일어난 사건이기 떄문에 변경되거나 미완성이어서는 안된다.
    - 이전의 event 를 수정하거나 취소하거나 수정하기 위해서는 보정 이벤트를 발행할 수 있다.
    - 예를 들면 "id 123 reservation was cancelled" 이벤트는 이전의 123 이라는 id 를 가진 reservation 을 취소하는 이벤트를 의미한다
  - Event 는 one-way 메시지다.
    - event 는 단일 출처 (single source, publisher) 여야 한다.
    - 하지만 수신자 (recipients, subscribers) 는 여럿이 될 수 있다
  - **일반적으로 Event 는 해당 이벤트에 포함되는 추가적인 정보를 포함한다.**
  - 이벤트 소싱에서 이벤트는 비즈니스를 포함해야 한다.
    - 이벤트는 그 자체로도 비즈니스 용어로 설명될 수 있는 것이 좋다.

- Events 는 애그리거트와 관련이 있다.

- 이벤트와 이벤트 소싱에 관련이 깊은 애그리거트의 두가지 특징을 알아야 한다.
  - 애그리거트는 관련있는 entity 에 대해서 일관성을 보장해야 한다. (consistency boundaries)
    - 그러므로 애그리거트의 event 를 이용해서 해당 트랜잭션에 참여하는 다른 애그리거트로 특정 사건이 일어났음을 알릴 수 있다.
  - 모든 애그리거트는 유일한 ID 를 가지고 있다. 그러므로 해당 ID 를 통해서 특정 이벤트의 소스였던 애그리거트를 기록할 수 있다.



## 3.1. Event Sourcing, 이벤트 소싱이란 무엇인가

- 이벤트 소싱이란 애플리케이션의 현재 상태의 변경 모두를 저장하고 이를 이용하여 애플리케이션의 상태를 유지하는 방법이다.

- 예를 들어 컨퍼런스 관리 시스템에서는 컨퍼런스의 좌석에 대해서 누군가가 예약을 시도할 때, 다른 사람이 예약하고 있는지 혹은 이미 예약이 완료되었는지에 대한 상태를 계속해서 추적해야 할 필요가 있다.

- 그래서 시스템은 다음과 같은 두가지 방법을 통해서 전체 예약을 관리할 수 있다.
  - 특정 컨퍼런스의 총 예약 수(`total number of bookings`) 를 관리하는 방법이 있다.
    - 누군가가 예약을 하거나 취소를 한다면 총 예약 수 (`total number of bookings`) 를 계속해서 조정해준다.
    - 특정 컨퍼런스 테이블에 integer 형태로 칼럼을 추가하는 형태를 떠올릴 수 있다.
  - 각각 컨퍼런스에 예약하거나 (`booking`) 취소하는 (`cancellation`) 이벤트를 저장하는 방법이 있다.
    - 현재 총 예약 수를 확인하기 위해서는 관련된 이벤트를 모두 replay 한 뒤 계산을 통해서 확인할 수 있다.

- 편의상 첫번째 방식을 ORM 을 사용한 방식이라고 하고 두번쨰 방식을 Event Sourcing 을 사용한 방식이라고 부르겠다



## 3.2. ORM 을 사용한 방식

- 그림 1을 보면 총 예약 수를 저장하는 첫번째 접근 방식을 보여준다.

1. [*ProcessManager](https://github.com/dhslrl321/cqrs-journey-guide-korean/blob/master/terms/Process Manager Patterm.md) 나 UI 가 Conference ID 157 로 두 명의 참석자에 대해서 좌석을 예매하라는 command 를 보낸다. 이 command 든 **SeatsAvailability** 애그리거트의 command handler 에 의해서 처리된다.
2. 필요하다면 ORM 을 이용해서 instance 를 생성하고 저장할 수 있다. ORM 은 DB 에서 총 예약 수가 존재하는 conference 를 조회한다.
3. comamnd handler 는 예약을 위해 aggregate 인스턴스를 통해서 business method 를 호출한다.
4. **SeatsAvailability** 애그리거트는 도메인 로직을 수행한다. 이 상황에서는 해당 컨퍼런스에 대해서 새로운 총 예약 수를 다시 계산한다.
5. ORM 은 애그리커트 인스턴스에 대한 정보들을 DB 에 저장한다. ORM 레이어는 Update 연산을 수행하게 된다.

[![image](https://user-images.githubusercontent.com/48385288/189571958-32a91499-bbb7-47b4-ad4a-daeefbaccb33.png)](https://user-images.githubusercontent.com/48385288/189571958-32a91499-bbb7-47b4-ad4a-daeefbaccb33.png)

- 위 그림은 아주 간소화된 process 를 보여준 것이다. 실제로는 ORM Layer 에 의해서 수행되는 mapping 은 훨씬 복잡할 것이다.

- 당신은 load 하고 save 하는 과정에서 발생하는 consistency, reliability, scalability 등에 대해서 고려해야 한다.



## 3.3. 두가지 방식 비교하기, Event Sourcing 을 사용한 방식

- 위 그림은 두번째 접근법인 RDBMS 와 ORM 대신 Event Sourcing 을 사용한 방식이다.

> 당신은 Event Store 를 RDBMS 를 사용해서 구현할 수도 있다. 관계형 스키마를 사용하는 것은 첫번째 접근법에서 사용된 ORM 을 사용하는것 보다 훨씬 간단하게 해준다.

- 아래 나오는 단계가 아래 그림에서 표현되는 Event Sourcing 을 사용한 방법이다.

- 1단계, 3단계, 4단계는 ORM 을 사용한 방법과 동일하다.

1. [*ProcessManager](https://github.com/dhslrl321/cqrs-journey-guide-korean/blob/master/terms/Process Manager Patterm.md) 나 UI 가 Conference ID 157 로 두 명의 참석자에 대해서 좌석을 예매하라는 command 를 보낸다. 이 command 든 **SeatsAvailability** 애그리거트의 command handler 에 의해서 처리된다.
2. 애그리거트 인스턴스가 **SeatsAvailability** 애그리거트 157 에 속하는 모든 이벤트를 query 한 결과로 채워진다.
3. comamnd handler 는 예약을 위해 aggregate 인스턴스를 통해서 business method 를 호출한다.
4. **SeatsAvailability** 애그리거트는 도메인 로직을 수행한다. 이 상황에서는 해당 컨퍼런스에 대해서 새로운 총 예약 수를 다시 계산한다.
5. 시스템은 event store 에 해당 애그리거트에 두개의 새로운 예약을 추가한다.

[![image](https://user-images.githubusercontent.com/48385288/189574546-162e5aa4-a3dd-4d65-bb9a-e8c9e4feeec9.png)](https://user-images.githubusercontent.com/48385288/189574546-162e5aa4-a3dd-4d65-bb9a-e8c9e4feeec9.png)

- 두번쨰 접근 방식은 ORM 의 복잡한 관계형 스키마를 대체하기 때문에 훨씬 단순하다.

> 자나: CQRS/ES 패턴은 구현 기술의 변경을 쉽게 해줍니다. 예를 들어서 이벤트 스토어의 프로토타입으로 file-based 기술로 선정했고 이후에 클라우드 서비스로 바꾸는것 처럼 말입니다.

- DB 는 오로지 특정 애그리거트 ID 에 속하는 이벤트만을 조회하고 이벤트를 저장 (appending) 만 하면 된다.

- 성능 및 확장성과 최적화를 고려해야 하지만, 이러한 최적화가 신뢰성과 일관성에 미치는 영향은 훨씬 이해하기 쉬워야 한다

> 참고로 이벤트의 스냅샷을 사용해서 전체 이벤트 목록을 쿼리하고 Replay 하는 데에 캐싱할 수 있다.

- 당신은 애그리거트가 저장된 이벤트들을 쿼리하여 현재 상태로 다시 build 할 수 있도록 하는 매커니즘이 있는지 확인해야 한다.

- 두번쨰 접근 방식으로 얻은 것은 컨퍼런스 예약에 대한 예약 (`booking`) 과 취소 (`cancellation`) 에 대해 발생한 사건들의 기록이다.

- 따라서 이벤트 스트림이 단일 진실 공급원이 된다. (only source of truth)

- 이벤트를 쉽게 재생하고 시스템 상태를 임의의 시점으로 복원할 수 있으므로 다른 애그리거트나 엔티티를 만들 필요가 없다.

- 회계(accounting), 정산(settlement) 같은 일부 영역에서 이벤트 소싱은 이미 잘 적용되어 있다.

- accounting 시스템에서는 시스템의 현재 상태를 항상 재구성할 수 있는 개별 트랜잭션을 저장한다.

- 이벤트 소싱은 특정 도메인에서 유용한 장점을 가져올 수 있다.



## 3.4. 왜 Event Sourcing 을 사용해야 할까

- 지금까지, 이벤트 소싱을 도메인의 애그리거트와 관련된 이벤트의 완전한 이력 (complete history of the events) 을 저장한다는 사실이라는 것으로 설명했다.

- accounting 이나 settlement (정산이나 회게와 같은) 어떤 도메인에서는 이벤트의 완전한 이력 (history of the events) 과 이벤트의 불변성 (immutable) 이 매우 중요한 기능이다.

- 트랜잭션이 발생한 후에는 삭제하거나 변경할 수 없지만 필요한 경우 수정 또는 삭제 (reversing) 트랜잭션을 일으킬 수도 있다.

- 아래의 리스트는 Event sourcing 을 사용하면 발생하는 추가적인 장점이다. 하지만 역시 중요한 것은 특정 도메인에 따라서 장점들이 다를 수 있다는 것이다.
  - Performance
  - Simplication
  - Audit trail
  - Integration with other subsystems
  - events history 에서 추가적인 비즈니스 가치 도출
  - production troubleshooting
  - fixing errors
  - testing
  - flexibility

### 3.4.1. Performance, 성능

- 이벤트는 불변하기 때문에 오로지 삽입 연산만 수행가능하다.

- 이벤트는 simple 하고 standalone object 인 사실 때문에 관계형 모델 저장 방식보다 더 나은 성능과 확장성을 가질 수 있다.

### 3.4.2. Simplication, 단순화

이벤트는 과거에 시스템에서 일어난 사건을 기술하는 simple objects 이다.

이러한 event 를 saving 만 하기 때문에 object-relation 과 db 의 패러다임 불일치로 불리는 복잡성을 훨씬 단순화 할 수 있다. 즉, 도메인 객체의 불필요하게 복잡한 저장 과정을 단순화 할 수 있다.

### 3.4.3. Audit trail, 감시 기록

- 이벤트는 불변하고 system 의 상태를 모두 저장한다.

- 그렇기 떄문에 시스템에서 발생한 모든 내용들을 audit 할 수 있다

### 3.4.4. Integration with other subsystems

- 이벤트는 다른 subsystem 과 통신하는 좋은 수단이다.

- event store 는 application 의 상태 변화에 관심있어 하는 또 다른 subsystem 에게 publish 할 수 있다.

### 3.4.5. events history 에서 추가적인 비즈니스 가치 도출

- 이벤트를 저장함으로써 특정 시점의 이벤트들만 조회할 수 있기 떄문에 시스템의 특정 시점으로 되돌릴 수 있는 힘이 생겼다.

- 이것은 시스템에 대한 비즈니스의 역사를 대변하는 것이다.

- 이벤트를 저장할 경우 미래에 가치가 있다고 판단될 수 있는 정보를 계속해서 갖게 되는 것이다

### 3.4.6. production troubleshooting

- 이벤트 스토어를 사용해서 프로덕션 이벤트 저장소의 사본을 가져오고 테스트 환경에서 replay 하여 프로덕션 system 의 문제를 빠르게 해결하는데 도움이 된다.

- 프로덕션 시스템에서 문제가 발생한 시각을 알고 있으면 해당 시점까지의 이벤트 스트림을 replay 하여 정확히 무슨 일이 일어났는지도 관찰할 수 있다.

### 3.4.7. fixing errors

- 시스템에서 잘못된 값을 계산하는 코딩 실수를 발견할 수 있다.

- 코딩 오류를 수정하고 저장된 데이터 항목에 대해서 새 버전의 코드를 기반으로 값을 올바르게 계산하도록 코드 오류를 수정하고 이벤트 스트림을 재생할 수 있다

### 3.4.8. testing

- 애그리거트의 모든 상태 변경은 이벤트로 저장된다.

- 따라서 단순히 이벤트를 확인함으로써 어떤 command 가 특정 애그리거트에 영향을 미쳤는지 테스트할 수 있다.

### 3.4.9. flexibility

- 일련의 이벤트를 통해서 특정 view 를 생성하는 것도 유연하게 가능하다



## 3.5. 이벤트 소싱의 고민 거리

### 3.5.1. 성능

- 이벤트 소싱은 일반적으로 업데이트 성능을 향상시키지만 애그리거트 상태와 관련된 모든 이벤트에 대해서 이벤트 스토어를 쿼리하여 도메인 개체 상태를 로드하는데 걸리는 시간이 오래 걸릴 수 있다.

- 스냅샷을 사용한다면 최신 스냅샷으로 돌아가야 해당 시점부터 이벤트를 replay 할 수 있기 떄문에 로드해야하는 데이터의 양을 제한할 수 있다.

- 스냅샷 shanpshot 에 대해서는 다음 챕터에서 자세히 설명할 것이다

### 3.5.2. versioning

- 미래에 어느 시점에서는 특정 이벤트의 스키마를 변경해야할 수도 있다.

- 시스템은 이러한 이벤트 유형에 대해서 여러 버전을 처리할 수 있는 방법에 대해서 고려해야 한다

### 3.5.3. querying

- 이벤트 스트림을 재생하여 개체의 현재 상태를 로드하는 것은 쉽지만 "total value 가 $250 이상인 모든 주문(order) 찾기" 와 같은 쿼리를 실행하는 것은 어렵거나 비용이 많이 든다.

- 그러나 CQRS 패턴을 함께 사용하는 경우엔 이러한 쿼리를 위해 존재하는 materialized view 를 통해서 문제를 해결할 수 있긴 하다



## 3.6. CQRS 와 ES

- CQRS 와 Event Sourcing 은 자주 언급된다.

- 각각은 서로의 상호보완적인 관계이다.

- CQRS에서 이벤트가 write-side 데이터 저장소에서 read-side 데이터 저장소로 애플리케이션 상태의 push synchronization 을 형성할 수 있다고 했다.

- 일반적으로 read side 의 데이터 저장소에는 비정규화된 데이터가 포함되어있다. (materialized view)

- 이벤트 스토어에 저장되는 이벤트를 사용해서 write side 에서 발행되는 모든 업데이트를 read side 로 전파할 수 있다.

- read side 에서는 이벤트에 포함된 정보를 사용해서 비정규화된 뷰를 유지할 수 있게 된다.

>  CQRS 는 Event Sourcing 없이도 존재할 수 있지만 Event Sourcing 은 CQRS 없이 존재할 수 없다.

- 아래의 그림은 CQRS 와 ES 를 함꼐 사용한 일련의 예 이다.

[![image](https://user-images.githubusercontent.com/48385288/189587376-ec56f6ee-908c-4439-8a60-1d10cbdf2b7a.png)](https://user-images.githubusercontent.com/48385288/189587376-ec56f6ee-908c-4439-8a60-1d10cbdf2b7a.png)

- write side 에서 이벤트를 이벤트 스토어에 저장한 후 이벤트를 publish 하는 방법에 대해서 주목하라.

- 이렇게 애그리거트가 이벤트 스토어에 이벤트를 저장하고 이벤트를 read side 에 publish 하는 경우, [*two phase commit](https://github.com/dhslrl321/cqrs-journey-guide-korean/blob/master/terms/two phase commit.md) 을 할 필요가 없다.

- 이런 방식으로 read side 데이터를 거의 실시간으로 최신 상태로 유지할 수 있다.

- transport mechanism 에 의해서 약간의 지연이 있을 수 있으며 4장 “A CQRS and ES Deep Dive” 에서 이러한 지연의 가능성과 결과에 대해서 설명한다.

- 또한 write side 에서 이벤트 스토어의 이벤트를 재생하여 언제든지 처음부터 read side 의 view 를 다시 생성할 수 있다.

- 또한 다른 바운디드 컨텍스트도 동일한 이벤트를 구독하려는 경우 신중하게 replay 를 수행해야 한다.

- 이벤트의 중복 스트림이 다른 바운디드 컨텍스트의 일관성을 보장하지 않을 수 있다.

- CQRS 는 read side 와 write side 의 서로 다른 데이터 저장소를 사용하도록 강제하지 않는다.

- write side 의 제 3 정규화가 된 스키마와 해당 스키마에 대한 비정규화된 뷰 집합이 있는 단일 RDBMS 를 사용할 수도 있다.

- 그러나 Event Replay 는 read side 와 write side 를 동기화하는 매우 편리한 메커니즘이다



## 3.7. 독립형 Event Sourcing

- 이벤트 소싱을 CQRS 와 함께 사용하지 않을 수 있다.

- 일부 시나리오에서는 애플리케이션의 상태를 재구축하고, 새로운 비즈니스 데이터에 대한 이벤트를 식별하고 replay 하며 데이터 저장을 단순화하는 기능이 유용할 수 있지만 이 가이드에서는 CQRS 패턴의 컨텍스트에서 이벤트 소싱을 사용하는데에 집중한다

  

## 3.8. 이벤트 스토어

- 이벤트 소싱을 사용하는 경우 이벤트를 저장하고 애그리거트 인스턴스와 연결된 이벤트 스트림을 반환하는 메커니즘이 필요하기 떄문에 이벤트를 replay 해서 애그리거트를 특정 상태로 만들 수 있다.

- 이러한 저장 메커니즘을 바로 이벤트 스토어, Event Store 라고 한다.

- 자체적으로 이벤트 스토어를 구현하거나 Jonathan Oliver의 EventStore 와 같은 외부 솔루션을 사용할 수도 있다.

- 소규모 이벤트 스토어는 비교적 쉽게 구축이 가능하지만 프로덕션 품질과 확장 가능한 형태로 구축하는 것은 더욱 challenging 하다.

### 3.8.1. Event Store 의 기본 요구사항

- 일반적으로 CQRS 패턴을 구현할 때는 애그리거트는 이벤트를 발생시켜서 다른 애그리거트, Process Manager, 읽기모델및 다른 바운디드 컨텍스트와 같은 이해관계자에게 정보를 publish 한다.

- 이벤트 소싱을 사용할 때 이러한 과정이 이벤트 스토어에 저장된다.

- 이렇게 된다면 이벤트를 사용하여 해당 애그리거트와 연결된 이벤트 시퀀스를 통해 다른 애그리거트로 상태를 전파할 수 있다.

- 따라서 하나의 애그리거트에서 이벤트가 발생되면 다음 두가지가 동시에 일어나야 한다.

1. 시스템은 이벤트를 이벤트 저장소에 저장한다.
2. 시스템은 이벤트 를 publish 해야 한다.

> 참고로 모든 이벤트에 subscriber 가 존재하는 것은 아니다. 애그리거트의 일부 속성을 유지하기 위해서 이벤트를 발행시키는 경우도 존재한다.

- 시스템이 애그리거트의 현재 상태를 로드할 때마다 해당 애그리거트 인스턴스와 연결된 과거 이벤트 목록에 대해서 이벤트 스토어에 쿼리하는 과정이 필요하다

### 3.8.2. 저장소 구현 기술

- 이벤트는 복잡한 데이터 구조가 아니다.

- 일반적으로 애그리거트 인스턴스의 ID, version, 이벤트 자체의 payload 가 구성요소일 것이다.

- 이벤트를 저장하기 위해서 꼭 RDB 를 사용할 필요도 없다.

- NoSQL 이나 문서형 데이터베이스 혹은 파일 시스템을 사용할 수도 있다

### 3.8.3. 성능, 확장성 그리고 일관성

- 저장된 이벤트는 변경할 수 없으며 항상 저장된 순서를 보장해야 하며 이벤트를 저장하는 것은 간단하고 빨라야 한다.

- 관계형 데이터베이스를 사용하는 경우 이벤트 순서를 정의하는 필드와 애그리거트 ID 를 사용하여 레코드에 키를 저장해야 한다.

- 애그리거트 인스턴스에 많은 수의 이벤트가 포함되어 있는 경우 해당 애그리거트를 로드하기 위해 모든 이벤트를 탐색해야 하므로 오랜 시간이 걸릴 수 있다.

- 이 시나리오에서 고려할만한 한 가지 옵션은 바로 스냅샷이라는 매커니즘이다.
  - 이벤트 스토어의 전체 이벤트 스트림 외에도 최근 특정 시점의 애그리거트 상태를 스냅샷으로 저장할 수 있다.
  - 애그리거트 상태를 다시 로드하려면, 가장 최근의 스냅샷을 조회하고 이후의 모든 이벤트를 재생한다.

  - 쓰는 처리 중에 스냅샷을 생성할 수 있다. 예를 들어서 100 개의 이벤트마다 스냅샷을 생성하는 형태로도 가능하다
    ***참고로 스냅샷은 DB 인스턴스 성능에 따라서 그 빈도가 결정될 수 있다.***
  - 스냅샷을 생성하기 위한 최적의 시간을 결졍하기 위해서 다양한 길이의 이벤트 스트림을 재생하는 데 걸리는 시간을 측정해야 한다.
  - 또는 이벤트 스트림을 반복적으로 재생하지 않도록 메모리에 많이 로드되는 애그리거트 인스턴스를 캐싱할 수 있다.
  - 이벤트를 저장할 때 해당 이벤트도 publish 되어야 한다.
  - 시스템의 일관성을 유지하기 위해서 두 작업이 함께 성공하거나 함께 실패해야 한다.
  - 기존에는 이것을 지원하기 위해서 two phase commit 을 사용하는 것이 있다.
  - 실제로 많은 데이터베이스와 메시징 플랫폼에서 two phase commit 트랜잭션에 대해 지원이 제한되어있다.

> two phase commit 을 사용하면 시스템의 성능과 확장성이 제한될 수도 있다.
>
> 이와 관련해서는 Gregor Hohpe 의 "Your Coffee Shop Doesn’t Use Two-Phase Commi" 을 참고하라







# 4. CQRS 와 Event Sourcing 의 Deep Dive



## 4.1. 소개

- 이번 챕터에서는 이전 장의 몇가지 핵심 사항에 대한 간략한 요약으로 시작해서 CQRS 패턴과 Event Sourcing 과 관련된 몇가지 중요한 개념을 더욱 자세히 탐구한다
  - Read Models and Write Models

  - Commands And Data Transfer Objects

  - DDD and aggregates

  - Data And Normalization

  - Events and Event Sourcing

  - Eventual Consistency

    

## 4.2. Read Models and Write Models

- CQRS 패턴은 객체의 상태를 수정하는 것과 조회하는 것의 책임을 분리한다.

- 이를 쓰기모델과 읽기모델이라고 하는데, 이러한 분리의 이점은 단일 책임 원칙을 적용하여 코드를 명확하고 단순화하는 것이다.

- 객체는 데이터 수정 또는 쿼리 둘 중 하나를 담당하게 된다.

  

## 4.3. Commands And Data Transfer Objects

- 사용자가 데이터를 update 할 수 있도록 하는 일반적인 접근 방식은 DTO 를 사용하는 것이다.

- UI 는 application 에서 update 할 데이터를 DTO 로 검색하고, update 한다.

- DTO 가 application 에 요청되면 해당 DTO 의 정보를 토대로 DB 와 상호작용헌다.

- 이러한 방식은 데이터 중심적이며 CRUD 작업에 매몰되는 경향이 있다.

- 이러한 방식이 많은 application 에서 효과적으로 사용되고 있지만 일부 경우에는 DTO 대신 Command 를 application 에 보내는 것이 더 유용할 수 있다.

- command 는 데이터 중심이 아닌 행동 중심이며 도메인의 작업을 직접 나타내며 사용자에게 더욱 직관적일 수 있다.

- 또한 DTO 보다 더 효과적으로 사용자의 의도를 표현할 수 있으며 CQRS 에서는 읽기모델은 데이터를 DTO 로 UI 에 반영하고 쓰기모델로 command 를 전송한다



## 4.4. DDD and aggregates

- command 를 사용하면 도메인과 관련된 동작과 더 밀접하게 일치하는 UI 를 만들 수 있다.

- 이와 관련해서 도메인 개념을 기반으로 consistency boundaries 를 모델링하는 방법으로 aggregate 에 초점을 맞춘 DDD 접근법이 있다.

- DTO 대신 command 와 aggregate 를 사용하는 이점 중 하나는 application 에서 locking 및 concurrency 관리를 단순화 할 수 있다는 것이다



## 4.5. Data And Normalization

- application 에서 CQRS 패턴은 데이터와 object 를 분리하는 것이다.

- 쓰기모델은 완전히 정규화되어 write 에 최적화 된 데이터베이스를 사용할 수 있다.

- 읽기모델 은 애플리케이션이 읽기 작업에 특화된 형태로 비정규화될 수 있고 이에 맞는 데이터베이스를 사용할 수 있다.

- 각 데이터베이스는 특정 작업에 최적화 되어있으므로 성능이 향상되며 locking 이 더욱 간단해진다.

- write side 에서는 locking 이 query 에 미치는 영향에 대해 더 이상 걱정할 필요가 없으며 read side 에서는 DB 가 읽기 전용일 수 있다.

  

## 4.6. Events and Event Sourcing

- read side 와 write side 모두 RDB 를 사용하는 경우 write side 에서 테이블에 대해서 CRUD 작업을 계속 수행하고 write side 의 정규화된 테이블에서 비정규화된 테이블로 변경 사항을 push 하는 메커니즘이 필요하다.

- 쓰기모델에서 변경 사항을 event 로 표현하는 경우, 해당 변경 사항들을 모두 Event 로 만들고 db 에 이들을 저장할 수 있다.

- 동일한 이벤트를 사용해서 변경사항을 read side 로 push 할 수도 있다.

- 이러한 이벤트를 사용하여 read side 에서 query 를 지원하도록 구조화된 데이터가 포함된 데이터의 projection 을 작성할 수도 있다

  

## 4.7. Eventual Consistency

- application 에서 단일 데이터베이스를 사용하는 경우 locking 에 따라 쿼리에서 반환되는 record 의 버전이 결정된다.

- 쿼리가 여러 테이블의 레코드를 조인하는 경우 이 프로세스가 매우 복잡할 수 있는데, UI 에서 렌더링 되자 마자 다른 프로세스나 사용자가 해당 데이터를 수정한다면 올바르지 않은 데이터가 표현될 수 있다.

- 데이터를 write side 저장소와 read side 저장소로 분리하는 경우 데이터를 쿼리할 때 오래된 데이터일 수 있지만 결국 write side 의 데이터와 일치하게 될 것이다.

- 이렇게 하면 application 을 더 단순화하고 여러 사용자가 write side 에서 동일한 데이터를 동시에 수정하려고 하는 상황에서 효과적일 수 있다.



## 4.8. 도메인 모델에서 aggregate 정의하기

- DDD 접근법에서 애그리거트는 consistency boundary 를 정의한다.

- 일반적으로 CQRS 패턴을 구현할 때, 쓰기모델에 있는 클래스가 애그리거트를 정의한다.

- 애그리거트는 command 의 수신자이며 영속의 단위 (units of persistence) 이다.

- 애그리거트의 인스턴스가 command 를 처리하고 해당 상태가 변경된 후 시스템은 인스턴스의 새 상태를 스토리지에 저장해야 한다.

- 애그리거트는 여러 관련 객체로 구성될 수 있다. 예를 들어 주문 애그리거트는 여러 개의 OrderItem 을 포함할 수 있으며 모두 함께 있어야 완벽한 하나의 Order 애그리거트가 된다.

- 애그리거트의 경계를 올바르게 식별했다면 여러 애그리거트 인스턴스를 함께 유지하기 위해서 트랜잭션을 사용할 필요가 없다.

- 애그리거트가 여러 엔티티로 표현되는 경우 한 엔티티를 Aggregate Root 로 지정해야 하고 한 Aggregate 의 접근은 오로지 Aggregate Root 를 통해서만 수행되어야 한다.(1:N)



## 4.9. 애그리거트와 ORM

- 애그리거트를 저장하기 위해서 ORM 을 사용할 떄, 최소한의 코드가 애그리거트 클래스 내부에 위치하게 된다.

- 아래 나오는 코드 샘플은 `AggregateRoot` 인터페이스와 Order 애그리거트를 표현한 코드이다. 이것은 ORM 을 사용하여 저장할 수 있는 애그리거트의 구현을 보여준다.

```java
public interface AggregateRoot {
    Long getId();
}

public class Order implements AggregateRoot {

    private Long id;
    private List<SeatQuantity> seats;

    @Override
    public Long getId() {
        return null;
    }

    public void updateSeats(List<SeatQuantity> seats) {
        this.seats = convertItems(seats);
    }
}

public class SeatQuantity {
    // ...
}
```



## 4.9. 애그리거트와 이벤트 소싱

- 만약 event sourcing 을 사용한다면, 당신의 애그리거트는 command 에 대한 처리로 발생하는 모든 상태 변화를 저장할 이벤트를 생성해야 한다.

- 아래의 코드 샘플은 `EventSourced` 인터페이스와 abstract 클래스를 보여준다.

> 이것은 event sourcing 을 통해 저장될 수 있는 애그리거트의 구현에 대한 접근법이다
>

```java
public interface EventSourced {
    String getId();
    int getVersion();
    List<VersionedEvent> getEvents();
}


public class AbstractEventSourced implements EventSourced {


    @Getter
    private final String id;
    @Getter
    private int version = -1;
    @Getter
    private final Map<Type, Action<VersionedEvent>> handlers = new HashMap<>();
    private final List<VersionedEvent> pendingEvents = new ArrayList<>();

    protected AbstractEventSourced(String id) {
        this.id = id;
    }

    @Override
    public List<VersionedEvent> getEvents() {
        return pendingEvents;
    }

    protected void handles(Action<VersionedEvent> handler) {
        this.handlers.put(handler.getType(), handler);
    }

    protected void loadFrom(List<VersionedEvent> pastEvents) {
        for (VersionedEvent e : pastEvents) {
            this.handlers.get(e).invoke(e);
            this.version = e.getVersion();
        }
    }

    protected void update(VersionedEvent e) {
        e.setSourceId(this.id);
        e.setVersion(this.version + 1);
        this.handlers.get(e.getType()).invoke(e);
        this.version = e.getVersion();
        this.pendingEvents.add(e);
    }
}

public class Order extends AbstractEventSourced {

    private List<SeatQuantity> seats;

    protected Order(String id) {
        super(id);
        super.handles(null);
    }

    public Order(String id, List<VersionedEvent> history) {
        super(id);
        super.loadFrom(history);
    }

    public void updateSeats(List<OrderItem> seats) {
        super.update(new OrderUpdatedEvent(convert(seats)));
    }

    private Object convert(List<OrderItem> seats) {
        return null;
    }
}
```

- 이 예제에서, `updateSeats()` 메서드는 직접적으로 aggregate 의 상태를 바꾸지 않고 새로운 `OrderUpdated` 라는 이벤트를 발행한다.

- abstract 클래스에 존재하는 update 메서드는 event store 의 event stream 에 추가할 pending events 에 추가하고, OnOrderUpdated 이벤트 핸들러를 호출하여 애그리거트의 상태를 업데이트 하는 책임을 가지고 있다.

> 마커스: 우리는 aggregate 가 infrastructure 관련된 코드로부터 오염되는 것을 피하기 위해서 많은 노력을 하였습니다. 애그리거트 클래스는 도메인 모델과 도메인 로직으로 구성되어야 합니다.

- 이런 방식으로 다뤄지는 모든 이벤트는 애그리거트의 버전을 함께 업데이트 한다.

- 애그리거트의 생성자와 추상 클래스의 `loadFrom()` 메서드는 이벤트 스트림을 재생하여 집계의 상태를 다시 로드합니다.



## 4.10. Command 와 command handlers

- 이번 섹션에서는 CQRS 를 구현에 존재하는 command 와 command handlers 의 역할에 대해서 설명한다.

### 4.10.1. Commands

- Command, 명령은 필수적이다. command 는 시스템이 어떠한 작업이나 행동을 하도록 요청하는 것이다.

- 두개의 예시가 존재한다.

1. 컨퍼런스 X 에서 두 좌석을 예약하라. `book two places on conference X`
2. 연사자 Y 에게 Z 방을 배정하라. `allocate Y to room Z`

- command 는 보통 하나의 수신자에 의해서 단 한번 처리된다.

- 명령의 발신자와 수신자 모두 동일한 바운디드 컨텍스트에 위치해 있어야 한다.

- 다른 consistency boundary 에 별도의 책임이 있는 bounded context 가 당신이 보낸 그 command 를 수행하기 위해서 잘못된 책임의 작업을 할 가능성이 높기 때문에 다른 바운디드 컨텍스트에 명령을 보내서는 안된다.

- 그러나 process manager 는 시스템의 특정 바운디드 컨텍스트에 속하지 않기 때문에 command 를 보내도 크게 문제되지 않는다.

### 4.10.2. example code

- 아래의 코드 샘플은 Command 인터페이스와 그 구현체를 보여준다.

- command 는 단순히 DTO 역할만 수행하고 모든 command 에는 식별할 수 있는 unique Id 를 갖는다.

```
@Value
public class MakeReservation implements Command {

    String id;
    String conferenceId;
    String reservationId;
    int numberOfSeats;

    public MakeReservation(String conferenceId, String reservationId, int numberOfSeats) {
        this.id = UUID.randomUUID().toString();
        this.conferenceId = conferenceId;
        this.reservationId = reservationId;
        this.numberOfSeats = numberOfSeats;
    }
}
```



## 4.11. Command handlers

- command 는 특정 수신자 (일반적으로 애그리거트의 인스턴스) 에게 보내진다.

- 커맨드 핸들러는 다음과 같은 작업을 수행한다.

1. command 객체를 메시징 인프라스트럭처로부터 수신한다.
2. command 가 유효한지 validation 한다.
3. command 의 대상 애그리거트 객체를 찾는다. 이 과정에서 새로운 애그리거트를 생성할 수도 있고 이미 존재하는 애그리거트를 가져올 수도 있다.
4. 애그리거트의 적절한 메서드를 호출하고 command 에 존재하는 데이터를 파라미터로 전달한다.
5. 애그리거트의 새로운 상태를 저장한다.

- 일반적으로 command handler 를 구성해서 사용할 수 있다.

- 일반적으로, 특정 애그리거트의 타입에 맞는 모든 핸들러를 포함하는 클래스를 갖도록 command handler 를 구성한다.

- 메시징 인프라는 단일 commandHandler 에 단일 command 만 전달하도록 해야한다.

- command 는 한 명의 수신자에 의해 한 번만 처리되어야 한다.

- 다음 코드 샘플은 **Order** instance 에 대한 명령을 처리하는 CommandHandler 를 보여준다

```
@RequiredArgsConstructor
public class OrderCommandHandler {
    private final EventSourcedRepository<Order> repository;

    CommandHandler<RegisterToConference> makeReservationCommandHandler = command -> {
        // .. impl
    };
    CommandHandler<MarkSeatsAsReserved> markSeatsAsReservedCommandHandler = command -> {
        // .. impl
    };
    CommandHandler<RejectOrder> rejectOrderCommandHandler = command -> {
        // .. impl
    };
    CommandHandler<AssignRegistrantDetails> assignRegistrantDetailsCommandHandler = command -> {
        // .. impl
    };
    CommandHandler<ConfirmOrder> confirmOrderCommandHandler = command -> {
        // .. impl
    };
}
```

- 이 handler 는 Order 애그리거트의 다섯가지의 서로 다른 커맨드를 처리한다.
  - `RegisterToConference` 커맨드는 새로운 애그리거트 객체를 생성하는 command 의 예시이다.
  - `ConfirmOrder` 커맨드는 이전에 존재하는 애그리거트를 찾는 command 의 예시이다.

- 위의 두 명령은 영속성 객체에게 동일한 `save()` 메서드를 사용한다.

- 만약 바운디드 컨텍스트가 ORM 을 사용한다면, `find()` 와 `save()` 메서드가 repository 클래스에 위치하게 될 것이고 db 에서 조회하거나 저장하는 역할을 할 것이다.

- 만약 바운디드 컨텍스트가 이벤트 소싱을 사용한다면, `find()` 메서드는 애그리거트의 이벤트 스트림을 replay 하여 상태를 재생성할 것이다.

- 그리고 `save()` 메서드를 호출하게 된다면 새로운 event 를 이벤트 스트림에 저장하게 될 것이다.

> 명심하라! 애그리거트가 명령의 처리 결과로 생성된 경우, repository 가 애그리거트 인스턴스를 저장할 때 생성되었다는 이벤트가 publish 된다.



## 4.12. Command 와 Optimistic Concurrency

- command 와 관련된 흔한 시나리오에서 command 에 포함된 몇몇의 데이터는 UI 시스템을 통해서 user 로 부터 받은 것이거나, 읽기모델로 부터 제공된 데이터다.

- 예를 들어서, UI 가 읽기모델에게 쿼리하여 얻은 주문 리스트를 보여준다고 할 때, 유저는 해당 리스트들중 하나의 주문 정보를 선택할 것이다. 그리고 해당 주문 정보를 수정할 것이다.

- 그리고 UI 는 해당 주문 정보를 수정한다는 정보를 command 로 만들고 시스템에게 요청하면, 해당 command 를 받은 시스템은 쓰기모델을 이용해서 command 를 처라하게 된다.

- 그러나 이 eventual consistency 때문에, UI 가 읽기모델에서 검색하는 정보가 아직 쓰기모델에서 수행된 변경 사항과 일치하지 않을 수 있다.

- **이 문제에 대한 해결책은 읽기 모델과 명령에 버전 번호를 사용하는 것이다.**
  - 쓰기모델이 변경 사항에 대한 세부 사항을 읽기모델에게 보낼 때마다 애그리거트의 현재 version 정보가 함께 전달된다.
  - UI 가 읽기모델을 쿼리할 때, version 정보를 받고 쓰기모델로 보내는 command 에 해당 version 을 포함된다.
  - 쓰기모델은 command 의 version 정보를 애그리거트의 현재 버전 정보와 비교할 수 있으며, 서로 다른 경우 concurrency 예외를 발생시키고 작업을 중단할 수 있다.



## 4.11. Event 와 Event handlers

- 이벤트는 CQRS 구현에서 서로 다른 두개의 역할을 가질 수 있다.
  - Event Sourcing
    - 이전에 설명했듯이, event sourcing 은 애그리거트의 상태 변화를 모두 이벤트 스트림으로 저장함으로써 애그리거트 인스턴스의 상태를 영속화 하는 것이다.
  - Communications and Integration
    - 이벤트를 동일한 바운디드 컨텍스트나 혹은 동일하지 않은 바운디드 컨텍스트 사이의 서로 다른 애그러거트의 통신의 수단으로 사용할 수도 있다.
    - 이벤트는 해당 이벤트를 구독하고 있는 subscriber 에게 특정 사건이 일어났음을 알리기 위해서도 사용된다.

- 결국 종합해보면, 이벤트는 애그리거트의 상태를 저장하는 데에 사용될 수 있고 혹은 서로 다른 애그리거트간의 통신 수단으로도 사용된다.



## 4.12. Event 와 Intent

- 앞서 언급했듯이, 이벤트 소싱에서 이벤트는 애그리거트의 상태 변경들을 저장할 뿐만 아니라 사용자의 의도를 포착한다.

- 아래의 대화에서 볼 수 있듯이 비즈니스의 의도를 알아차리기는 쉽지 않다.

[![image](https://user-images.githubusercontent.com/48385288/193538311-fa50fe81-59cc-4e29-9414-4bc1df0522b5.png)](https://user-images.githubusercontent.com/48385288/193538311-fa50fe81-59cc-4e29-9414-4bc1df0522b5.png)



### 4.12.1 Intent(사용자 의도) 를 모델링하는 방법

- 이번 섹션에서는 SOAP 및 REST 스타일의 서로 다른 intent(사용자 의도) modeling 을 비교하며 차이점을 알아본다.

- 아래의 코드 샘플은 intent(사용자 의도) 를 모델링하는 두개의 서로 다른 방법을 보여준다.

- Example1. SOAP 스타일의 Event Log

```json
[
  { "reserved": { "seatType": "FullConference", "quantity": "5" } },
  { "reserved": { "seatType": "WorkshopA", "quantity": "3" } },
  { "purchased": { "seatType": "FullConference", "quantity": "5" } },
  { "expired": { "seatType": "WorkshopA", "quantity": "3" } }
]
```

- Example2. REST 스타일의 Transaction Log

```json
[
  {
    "insert": {
      "resource": "reservations",
      "seatType": "FullConference",
      "quantity": "5"
    }
  },
  {
    "insert": {
      "resource": "reservations",
      "seatType": "WorkshopA",
      "quantity": "3"
    }
  },
  {
    "insert": {
      "resource": "orders",
      "seatType": "FullConference",
      "quantity": "5"
    }
  },
  {
    "delete": {
      "resource": "reservations",
      "seatType": "WorkshopA",
      "quantity": "3"
    }
  }
]
```

- 첫 번째 접근법은 이벤트를 특정 애그리거트 유형과 결합시키는 행위 기반 계약 (action-based contract)을 사용한다.

- 이에 반해 두번째 접근 방식은 `resource` 필드를 이용하여 이벤트를 애그리거트의 유형과 연결하는 균일한 계약을 사용한다

> 이벤트가 실제로 어떻게 저장되는지는 별개의 문제이다. 앞선 이야기는 **이벤트를 어떻게 모델링하는가**에 대한 방법에 초점을 맞추고 있다.

- 첫 번째 접근법의 장점
  - Strong typing.
  - More expressive code.
  - 테스트가 용이하다.

- 두번째 접근법의 장점
  - 단순하고 일반적인 접근
  - 다른 시스템에서 사용하기 쉽다.
  - 여러 언어에서 제공하는 방법과 동일하다

### 4.12.2. Events

- 이벤트는 과거에 일어난 어떠한 사건이다.

- 애그리거트나 프로세스 매니저가 여러 구독자들을 위해서 비동기적으로 메시지를 발행된다.

- 예를 들면 다음과 같다.

```
SeatsUpdated`, `PaymentCompleted`, `EmailSent
```

- 아래에 나오는 샘플 코드는 Event 와 그 구현체를 보여주고 있다.

```java
public interface Event {
    e
}

@Value
public class SeatAdded implements Event {
    public Long conferenceId;
    public Long sourceId;
    public int totalQuantity;
    public int addedQuantity;
}
```

> 이벤트는 DTO 처럼 사용되지만 immutable 하게 다뤄져야 한다

- 아래의 코드 샘플은 이벤트 소싱에서 사용될 event 의 구현 을 보여준다.

- 특정 이벤트는 `VersionedEvent` abstract 클래스를 확장한다

```java
public interface VersionedEvent {
    Long getSourceId();
    int getVersion();
}

public class AbstractVersionedEvent implements VersionedEvent {
    Long sourceId;
    int version;

    @Override
    public Long getSourceId() {
        return sourceId;
    }

    @Override
    public int getVersion() {
        return version;
    }
}

public class AvailableSeatsChanged extends AbstractVersionedEvent {
    @Getter @Setter
    List<SeatQuantity> seats;
}
```

- `version` 프로퍼티는 애그리거트의 버전을 의미한다. 애그리거트가 새로운 이벤트를 받을 때 마다 버전이 증가한다.

### 4.12.3. Event Handlers

- 일반적으로 이벤트는 애그리거트 인스턴스나 프로세스 매니저와 같은 다양한 수신자에게 전달된다.

- EventHandler 는 아래와 같은 일들을 수행한다.

1. messaging infrastructure 로 부터 메시지를 받아들인다
2. 이벤트의 대상인 애그리거트 혹은 process manager 인스턴스를 찾는다. 아마도 새로운 애그리거트 인스턴스를 만들거나 기존에 존재하는 인스턴스를 찾는 과정일 것이다.
3. 이벤트에 담겨있는 정보를 애그리거트나 process manager 의 인스턴스가 가지고 있는 적절한 메서드의 파라미터로 전달함으로 호출한다.
4. 새로운 상태의 애그리거트나 process manager 인스턴스를 저장한다

### 4.12.4 Sample Code

```
public void handle(SeatsAdded event) {
    SeatsAvailability availability = repository.findBy(event.getConferenceId());

    if (Objects.isNull(availability)) {
        availability = new SeatsAvailability(event.getConferenceId());
    }

    availability.addSeats(event.sourceId, event.addedQuantity);

    repository.save(availability);
}
```

- 만약 바운디드 컨텍스트가 ORM 을 사용한다면, repository 에 존재하는 `findBy` 나 `save` 와 같은 메서드가 데이터베이스와 상호작용을 위해 존재할 것이다.
- 만약 바운디드 컨텍스트가 이벤트 소싱을 사용한다면, `findBy` 메서드는 애그리거트의 이벤트 스트림을 replay 하여 현재의 상태를 다시 만들 것이고 `save()` 를 호출하게 되면 애그리거트의 이벤트 스트림에 새로운 이벤트를 append 할 것이다



## 4.13. 최종 일관성과 CQRS

- CQRS 패턴과 최종적 일관성은 어떤 연관이 있을까?

- CQRS 패턴의 일반적인 구현은 하나의 노드는 쓰기 연산을 하고 다른 하나는 읽기 연산을 수행하는 분산 시스템이다.

- 만약 CQRS 를 구현한다고 한다면 양쪽의 데이터를 일관되게 유지하는 메커니즘이 필요하다.

- 이러한 동기화 과정은 한쪽에서만 쓰기 연산이 수행될 것이기 때문에 수정이 일어나면 단지 읽기 연산을 하는 쪽으로 변경 사항만 전달하면 되므로 크게 복잡하지 않다.

- 만약 양쪽 사이드 모두 항상 일관적이게 만들고 싶다면 (strong consistency), 아래의 그림과 같이 분산 트랜잭션을 도입해야 한다.

[![image](https://user-images.githubusercontent.com/48385288/196939711-4fc806ae-524a-4959-8f36-b9f31ff9d7c3.png)](https://user-images.githubusercontent.com/48385288/196939711-4fc806ae-524a-4959-8f36-b9f31ff9d7c3.png)

- 이러한 접근법에 대한 문제는 바로 성능과 가용성이다.

- **첫째로, 양쪽 사이드는 commit 하기 전까지 lock 을 잡고있어야 한다.**
  - 즉, 트랜잭션에 대한 잠금으로 인한 대기가 많아질 것이다.
  - 트랜잭션은 두개 이상의 노드가 포함될 수 있으며 여러 인스턴스를 추가해서 read side 를 scale out 하는 경우, 트랜잭션은 모든 인스턴스를 포함해야 한다

- **둘쨰로, 어떠한 이유로든 하나의 노드가 실패한다면 혹은 트랜잭션이 성공하지 못한다면, 전체 트랜잭션이 실패하게 된다.**
  - CAP 이론의 용어를 빌려보자면, consistecny 를 지키기 위해서 availability 를 포기해야 하는 상황이 온다.
  - 일관성에 대한 제약을 조금 줄이고 read side 가 결국 write side 와 일치하도록 하는 경우는 트랜잭션 범위를 변경할 수 있다.
- 아래의 그림은 변경 사항을 전파하기 위해서 신뢰할 수 있는 메시징 인프라를 활용해 read side 를 결국 write side 로 일치시키는 방법을 보여준다.

[![image](https://user-images.githubusercontent.com/48385288/196941948-51358927-fab4-408e-84e4-c2ddf2e6a0bb.png)](https://user-images.githubusercontent.com/48385288/196941948-51358927-fab4-408e-84e4-c2ddf2e6a0bb.png)

- 위 그림에서 여전히 트랜잭션이 존재하는 것을 볼 수 있다.

- 이 트랜잭션의 범위는 write side 의 데이터 저장소에 대한 변경 사항을 저장하고 변경 사항을 read side 에 push 하는 것을 포함한다.

- 이 방법은 메시징 인프라를 통해 대기열에 메시지를 빠르게 추가할 수 있다고 가정하면 앞서 이야기했던 일관성을 위한 전체 성능 저하 문제를 부분적으로 해결할 수 있다.

- 이러한 솔루션은 메시지 대기열이 read side 로 전달되는 메시지의 버퍼 역할을 수행하므로 더이상 모든 read side 의 인스턴스에 의존하지 않게 된다.

> 실제로, 메시징 인프라는 pub/sub 모델을 사용하고 일반적인 queue 를 사용하지는 않는다

- 이 세번쨰 예제는 분산 트랜잭션이 필요하지 않은 상황을 보여준다.

[![image](https://user-images.githubusercontent.com/48385288/196943915-b4075d03-9426-4522-a166-3f3bd780489f.png)](https://user-images.githubusercontent.com/48385288/196943915-b4075d03-9426-4522-a166-3f3bd780489f.png)

- 위 예제는 쓰기쪽 데이터 저장소의 기능에 따라서 다르다.

- 쓰기 측 모델이 데이터에 대한 모든 업데이트에 대한 응답으로 메시지를 보낼 수 있어야 한다.

- 이러한 접근 방식은 CQRS 와 이벤트 소싱을 결합하는 시나리오에 특히 적합하다.

- 이벤트 스토어가 메시지 대기열에 저장하는 모든 이벤트의 복사본을 보낼 수 있다면, 이 인프라 기능을 이용해서 read side 를 일관되게 만들 수 있다.



## 4.14. Optimizing the read-side

- read side 를 최적화 하기 위해서 염두해야 하는 목표가 4가지가 존재한다.

1. 데이터 쿼리에 대한 빠른 응답
2. resource utilization
3. 최소 지연
4. 최소 비용

- write side 로 부터 read side 를 분리함으로써, CQRS 패턴은 데이터를 읽기에 최적화 하기 위해서 read side 를 설계할 수 있도록 한다.

- 관계형 테이블을 비정규화거나 구체화하여 데이터를 저장할 수 있도록 한다.

- 이상적으로 데이터 수신자는 데이터에 대한 조인이나 기타 복잡하고 자원 집약적인 작업들로부터 해방되게 된다.

> 데이터에 대한 불필요한 작업을 없애는 방법에 대한 논의는 journey 4 장의 **Extending and Enhancing the Orders and Registrations Bounded Context** 섹션의 Querying the read side 를 확인할 수 있다.

- 시스템이 대량의 읽기 작업을 수용해야 하는 경우에 read side 만 독립적으로 확장할 수 있다.

- 확장을 수행할 때 이에 관한 데이터소스 역시 read 전용이기 때문에 더욱 쉽게 확장이 가능하다.

- 또한 응답 속도를 더욱 가속화하고 프로세스 리소스 활용도를 줄이기 위해 읽기 측면에서 데이터 캐싱을 선택할 수도 있다.

- 팀이 확장성을 위해 RI 를 설계한 방법에 대한 설명은 7장 *Adding Resilience and Optimizing Performance* 를 확인할 수 있다.

- 이 장의 앞부분의 최종적 일관성, eventualy consistency 섹션에서 CQRS 패턴을 구현할 떄 write side 의 업데이트와 read side 에서 표시되는 변경 사항 사이의 대기 시간을 줄이는 방법에 대해서 알아보았다.

- 하지만 이것도 만족하지 않고 더욱 지연을 최소화 하고싶을 수 있다.
  - read side 로 업데이트 정보를 전송하는 인프라에 충분한 리소스가 있는지 확인하고 read side 에 대한 업데이트가 효율적으로 이루어지도록 함으로써 지연을 최소화할 수 있다.



## 4.15. Optimizing the write side

- write side 의 최적화 핵심은 command 와 event 의 처리량을 극대화하는 것이다.

- 일반적으로 write side 는 ui 에서 command 를 받거나 다른 바운디드 컨텍스트로부터 발생하는 integration 이벤트를 수신할 때 특정 로직이 수행된다.

- messaging infrastructure 가 최소한의 지연으로 command 및 event message 를 제공하고, 도메인 모델의 처리량 및 속도 그리고 데이터소스와의 상호작용이 빠른지 확인해야 한다.

- 다음은 message 가 write side 로 전달되는 방식을 최적화하는 방법들이다.
  - messaging infrastructure 를 사용하지 않고 inline 으로 command 를 전달할 수도 있다.
    - 실패에 대한 탄력성 (resilience) 에 영향을 미칠 수 있다.
  - 일부 command 를 병렬로 처리한다.
    - 동시성 (concurrency) 에 대해 영향을 미칠 수 있다.

- 이벤트소싱을 사용하는 경우, 스냅샷을 사용하여 애그리거트의 상태를 로드하는 데 걸리는 시간을 줄일 수도 있다.
  - 애그리거트를 로드할 때 전체 이벤트 스트림을 replay 하는 대신, 상태의 최신 스냅샷을 로드한 다음에 스냅샷을 찍은 후 발생된 이벤트만 replay 한다.
  - 정기적으로 애그리거트를 위한 스냅샷을 만드는 매커니즘을 도입해야 한다.
  - 그러나 일반적인 이벤트스토어 스키마의 단순함을 감한할 때, 애그리거트의 상태를 로드하는 것은 일반적으로 매우 빠르다.
  - 스냅샷을 사용하는 것은 일반적으로 애그리거트에 매우 많은 수의 이벤트가 있을 때만 성능상 이점을 가져갈 수 있다.



## 4.16. Concurrency and aggregates

- 애그리거트와 command handler 의 간단한 구현은 애그리거트가 처리해야 하는 각 command 에 대해 애그리거트 인스턴스를 메모리에 로드할 것이다.

- 많은 수의 command 를 처리해야 하는 애그리거트의 경우, 모든 command 에 대해 다시 로드할 빌요가 없도록 애그리거트 인스턴스를 메모리에 캐시하기로 결정할 수 있다.

- 시스템에 메모리에 로드된 애그리거트의 단일 인스턴스만 있는 경우, 해당 애그리거트는 여러 글라이언트에서 전송되는 command 를 처리해야 할 수도 있다.

- 시스템이 큐를 통해 애그리거트 인스턴스에 command 를 전달하도록 순서를 보장하면 애그리거트가 command 를 순차적으로 처리하도록 할 수 있다.

- 또한 한 번에 하나의 command 만 CommandHandler 에 들어가기 때문에 애그리거트 스레드를 Thread Safe 하게 만들 필요도 없다.

- command 의 처리량이 훨씬 더 높은 시나리오에서는 다른 프로세스에서 여러 애그리거트 인스턴스를 메모리에 로드해야 할 수도 있다.

- 여기서 동시성 문제를 처리하기 위해서는 이벤트 소싱과 versioning 을 사용해야 한다.

- 각 애그리거트 인스턴스에는 이벤트를 저장할 때마다 업데이트되는 버전이 있어야 한다.

- **애그리거트 인스턴스에게 버전 정보를 부여하는 두가지 방법이 존재한다.**
  - **Optimistic**: 이벤트 스트림의 최신 이벤트가 현재 인메모리 인스턴스와 동일한 버전인 경우, 이벤트 스트림에 이벤트를 추가한다.
  - **Pessimistic**: 현재 인메모리의 인스턴스 버전보다 더 큰 버전 번호를 가진 이벤트 스트림의 모든 이벤트를 로드한다.



## 4.17. Messaging 과 CQRS

- CQRS 와 이벤트소싱은 두가지 유형의 메시지를 사용한다.

### 4.17.1. command 와 event

- 일반적으로 CQRS 패턴을 구현하는 시스템은 대규모 분산 시스템이므로 producer/consumer 및 publisher/subscriber 간의 메시지 전송에 신뢰할 수 있는 메시징 인프라가 필요하다.

- 단일 수신자가 있는 **command** 의 경우 일반적으로 queue topology 를 사용한다. 그리고 **event** 의 경우 여러 수신자가 있기 때문에 pub/sub 형태의 topology 를 사용한다.

- 이 가이드와 함께 제공되는 RI 는 메시징을 위해 windows azure service bus 를 사용한다. 7장 "Technology used in Reference Implementation" 에서는 이에 대한 추가 설명을 제공한다.

  

### 4.17.2. messaging 을 도입할 때 필요한 고려사항

- messaging 을 사용할 때, 몇가지 고려사항들이 존재한다.

- Event 와 Command 를 사용하여 CQRS 를 구현하기 위해 가장 중요한 몇가지 이슈들에 대해서 이야기한다.
  - message 중복
  - message 유실
  - message 순서 보장
  - 처리되지 않은 메시지

- #### message 중복

  - 메시징 인프라나 메시지를 수신하는 코드의 오류로 인해서 메시지는 수신자에게 여러번 전달될 수 있다.

  - 이러한 문제를 해결할 수 있는 두가지 접근법이 존재한다.
    - 메시지를 멱등하게 설계한다.
      - 메시지를 멱등하게 설계해서 중복 메시지가 들어오더라도 데이터의 일관성이 깨지지 않도록 설계하는 방법이다.
    - 중복 메시지를 찾는 로직을 구현한다.
      - 몇몇의 메시징 인프라는 중복 검출에 대한 설정 전략을 지원하기 때문에 직접 구현하지 않고 이러한 인프라의 도움을 받을 수도 있다.
  - 멱등 (idempotency) 에 대해서 더욱 자세히 알고싶다면 Pat Helland. 의“[Idempotence Is Not a Medical Condition](https://queue.acm.org/detail.cfm?id=2187821)” 에 대해서 확인하는 것도 추천한다

- **message 유실**
  - 메시지 유실에 대한 문제도 처리해야 한다.
  - 많은 메시징 인프라는 메시지가 손실되지 않고 수신자에게 적어도 한번 (At least once) 전달된다는 보증을 제공한다.
  - 메시지가 손실되었을 때를 감지하기 위해 구현할 수 있는 대안에 대한 전략에 대해서는 발신자에게 수신에 대한 승인을 구하거나 수신자가 message 를 받지 못하였는지 알 수 있도록 message 에 sequence 번호를 할당하는 handshake process 가 필요하다

- **message 순서 보장**
  - 메시징 인프라는 발신자가 메시지를 보낸 순서와 다른 순서로 수신자에게 메시지를 전달할 수 있다.

  - 일부 시나리오에서 메시지가 수신되는 순서는 중요하지 않을 수 있지만 또 다른 시나리오에서는 중요할 수 있다.
  - 메시지 순서가 중요하다면, 일부 메시징 인프라 구조는 순서를 보장해주는 제품들이 있을 것이다.
  - 그렇지 않다면 전송되는 메시지에 sequence 번호를 할당하여 주문 외 메시지를 감지할 수 있다.
  - 메시지를 올바른 순서로 정렬하기 까지 받은 메시지를 따로 저장하는 메커니즘을 process manager 에 해당 로직을 구현할 수도 있다.
  - 특정 그룹 내에서 메시지를 정렬해야 하는 경우, 관련 메시지를 단일 배치로 보낼 수도 있으니 참고하라

- **처리되지 않은 메시지**
  - 클라이언트는 대기열에서 메시지를 검색한 다음 메시지를 처리하는 동안 실패할 수 있는데, 제대로 처리되지 않은 상태에서 클라이언트가 메시지를 다시 수신한다면 메시지가 손실된다.
  - 일부 메시징 인프라를 사용하면 메시지 처리가 실패하였을 때 롤백할 수 있는 분산 트랜잭션의 일부로 인프라에서 메시지 읽기를 수행할 수 있다.

  - 또한 특정 메시지 인프라에서 제공하는 또 다른 접근 방식을 메시지 읽기를 two-phase operation 으로 처리하는 것이다.
    - 1. 먼저 메시지를 lock 한 뒤 읽는다.
      2. message handling 이 끝나면 완료로 표시하고, 대기열에서 제거된다.
  - 메시지가 완료로 표시되지 않으면, 메시지의 locking 시작이 초과되고 다시 읽을 수 있게 된다.



## 4.18. Event Versioning

- 시스템이 진화할수록, 시스템에서 사용되는 Event 자체도 함께 진화할 것이다.

- 에를 들어
  - 일부 이벤트는 시스템의 어떤 클래스에서도 더 이상 발생하지 않는다는 점에서 중복될 수 있다.
  - 시스템 내의 새로운 기능이나 해당 기능과 관련된 새로운 이벤트를 정의해야 할 수도 있다.
  - 기존 이벤트 payload 혹은 definition 을 수정해야 할 수도 있다.

- 이러한 시나리오에 대해서 설명해보겠다.

### 4.18.1. Redundant events, 중복 이벤트

- 시스템이 더 이상 특정 이벤트 타입을 사용하지 않는다면, 시스템에서 간단히 제거할 수 있다.

- 하지만 이벤트소싱을 사용한다면, event store 는 이 이벤트의 많은 인스턴스를 보유할 수 있으며, 이러한 인스턴스는 애그리거트 상태를 replay 하는데 사용될 수 있다.

- 일반적으로 이벤트 스토어의 이벤트를 immutable 로 취급하는데, 애그리거트는 시스템이 더 이상 해당 이벤트 타입으로 새로운 인스턴스를 발생하지 않더라도 이벤트 스토어에서 replay 될 때 이러한 이벤트를 계속해서 처리할 수 있어야 한다.

### 4.18.2.  새로운 이벤트 타입

- 시스템에 새로운 이벤트 타입을 추가하더라도 기존 동작에 영향을 미치지 않아야 한다.

- 일반적으로 새로운 이벤트 타입을 사용하는 것은 단지 새로운 기능이나 기능을 도입하는 것 뿐이다.

### 4.18.3.  존재하는 이벤트 payload 혹은 definition 정의

- 이벤트 타입 정의의 변경 사항을 처리하려면 시스템에 더 복잡한 변경이 필요하다.

- 예의 경우, 이벤트 스토어는 이전 버전의 이벤트 타입의 많은 인스턴스를 보유할 수 있으며, 시스템은 이후 버전인 이벤트를 발행시키거나, 다른 바운디드 컨텍스트는 동일한 이벤트의 다른 버전을 발행할 수 있다.

- 시스템은 동일한 이벤트의 여러 버전을 처리할 수 있어야 한다.

- **이벤트의 버전은 다양한 이유에 의해서 변경될 수 있다.**
  - 이벤트에 새로운 프로퍼티가 추가되었다.
  - 이벤트에 특정 프로퍼티가 삭제되었다.
  - 프로퍼티의 속성의 데이터 타입이 변경되었다.

> 이벤트의 sementic 의미가 변경되면, 기존 이벤트의 새 버전이 아닌 새로운 이벤트 유형으로 취급해야 합니다.

- **여러 버전의 이벤트 타입이 있는 경우, 여러 버전을 처리하는 방법에 대한 두가지 기본 선택사항이 있다.**

1. 도메인 클래스에서 여러 버전의 이벤트를 계속해서 지원한다.
2. 특정 로직을 통해 시스템에서 발생할 때 마다 이전 버전의 이벤트를 최신 버전으로 변환할 수 있다.

- **첫번째 방법의 경우 일반적으로 인프라를 변경할 필요가 없기 때문에 채택하기 쉽고 빠르다.**
  - 하지만 이 방법으로 계속할 경우 버전 별로 처리해야 하는 코드가 달라지므로 도메인 클래스가 오염될 것이다. 하지만 이벤트 정의에 많은 변화가 없을 것으로 보인다면 이러한 방법도 괜찮은 선택이다.

- **두번째 접근 방식은 도메인을 더 깔끔하게 만들 수 있다.**
  - 도메인 클래스는 각 이벤트 타입의 최신 버전만 지원하게 된다. 그러나 이전 버전의 이벤트를 최신으로 변환하려면 인프라를 변경해야 한다.
  - 여기서 문제는 이 변환 작업을 위해 인프라가 변경된다는 것이다.
  - 한가지 옵션인 메시징 인프라에 필터링 기능을 추가해서 이벤트가 수신자에게 전달될 때 EventHandler 에 변환 기능을 추가하여 자동 변환되도록 하는 것이다.
  - 이벤트 소싱을 사용하는 경우에 애그리거트로 hydration 과정에서 이벤트 스토어에서 read 할 때 이전 버전의 이벤트가 잘 변환되었는지 확인해야 한다.
  - 어떤 솔루션을 채택하던 이러한 변환 작업은 필수적인 것이다.
  - 직렬화 방식을 선택하면 다양한 버전의 이벤트를 더욱 쉽게 처리할 수 있다. 예를 들어서 JSON 역직렬화는 단순히 삭제된 속성을 무시하거나 객체가 역직렬화된 클래스에 새로운 프로퍼티에 기본 값을 세팅하게 할 수 있다.





# 5. 실습

이 지침은 두 개의 서로 다른 데이터 소스를 동기화하는 방법에 대한 개요를 제공합니다. 간단한 CQRS 앱에서 명령과 쿼리를 분리하여 그렇게 할 것입니다. 각 모듈은 이 패턴을 도입하는 다른 방법을 나타냅니다. 또한 각 모듈은 독립 실행형 [Spring Boot](https://spring.io/projects/spring-boot) 애플리케이션입니다.

## 5.1. 전제 조건

소프트웨어를 실행하는 데 필요한 것:

- 자바 8+
- [도커 작성](https://docs.docker.com/compose/)

## 5.2. 개요

샘플 애플리케이션은 신용 카드를 제공하는 간단한 도메인을 기반으로 합니다. 두 가지 사용 사례가 있습니다.

- 카드에서 돈을 인출할 수 있습니다( *인출* **명령** )
- 카드 인출 목록을 읽을 수 있습니다( **쿼리** )

중요한 것은 다음과 같습니다.

```
After a successful Withdraw command, a withdrawal should be seen in a result from list of withdrawals query.
```

따라서 명령과 쿼리의 상태를 일관성 있게 만드는 **동기화** 가 필요합니다 .

명령, 쿼리 및 동기화에 대한 색상 코드에 동의합시다. 그것은 우리의 그림을 일관되게 만들 것입니다.

[![색상 코드](https://github.com/ddd-by-examples/all-things-cqrs/raw/master/colorcode.jpg)](https://github.com/ddd-by-examples/all-things-cqrs/blob/master/colorcode.jpg)

## 5.3. 클래스에서 처리되는 명령 및 쿼리(CQRS 없음)

코드는 [in-one-class](https://github.com/ddd-by-examples/all-things-cqrs/tree/master/in-one-class) 모듈에서 찾을 수 있습니다.

앱 실행:

```
mvn spring-boot:run
```

샘플 인출명령:

```
curl localhost:8080/withdrawals -X POST --header 'Content-Type: application/json' -d '{"card":"3a3e99f0-5ad9-47fa-961d-d75fab32ef0e", "amount": 10.00}' --verbose
```

쿼리로 확인:

```
curl http://localhost:8080/withdrawals?cardId=3a3e99f0-5ad9-47fa-961d-d75fab32ef0e --verbose
```

예상 결과:

```
[{"amount":10.00}]
```

아키텍처 개요:

[![동급](https://github.com/ddd-by-examples/all-things-cqrs/raw/master/inoneclass.jpg)](https://github.com/ddd-by-examples/all-things-cqrs/blob/master/inoneclass.jpg)

REST API에 대한 자동 E2E 테스트는 [여기](https://github.com/ddd-by-examples/all-things-cqrs/blob/master/in-one-class/src/test/java/io/dddbyexamples/cqrs/CommandQuerySynchronizationTest.java) 에서 찾을 수 있습니다 .

```
    @Test
    public void shouldSynchronizeQuerySideAfterSendingACommand() {
        // given
        UUID cardUUid = thereIsCreditCardWithLimit(new BigDecimal(100)); //HTTP POST
        // when
        clientWantsToWithdraw(TEN, cardUUid); //HTTP GET
        // then
        thereIsOneWithdrawalOf(TEN, cardUUid);
    }
```

## 5.4. 명시적 동기화로 애플리케이션 서비스를 사용하는 CQRS

코드는 [명시적-with-dto](https://github.com/ddd-by-examples/all-things-cqrs/tree/master/explicit-with-dto) 모듈에서 찾을 수 있습니다. 동일한 버전이지만 JPA 엔터티가 있는 쿼리 결과는 [여기](https://github.com/ddd-by-examples/all-things-cqrs/tree/master/explicit-with-entity) 에서 찾을 수 있습니다 .

앱 실행:

```
mvn spring-boot:run
```

샘플 *인출* 명령:

```
curl localhost:8080/withdrawals -X POST --header 'Content-Type: application/json' -d '{"card":"3a3e99f0-5ad9-47fa-961d-d75fab32ef0e", "amount": 10.00}' --verbose
```

쿼리로 확인:

```
curl http://localhost:8080/withdrawals?cardId=3a3e99f0-5ad9-47fa-961d-d75fab32ef0e --verbose
```

예상 결과:

```
[{"amount":10.00}]
```

아키텍처 개요:

[![신청 절차](https://github.com/ddd-by-examples/all-things-cqrs/raw/master/appprocess.jpg)](https://github.com/ddd-by-examples/all-things-cqrs/blob/master/appprocess.jpg)

REST API에 대한 자동 E2E 테스트는 [여기](https://github.com/ddd-by-examples/all-things-cqrs/blob/master/explicit-with-dto/src/test/java/io/dddbyexamples/cqrs/CommandQuerySynchronizationTest.java) 에서 찾을 수 있습니다 .

```
    @Test
    public void shouldSynchronizeQuerySideAfterSendingACommand() {
        // given
        UUID cardUUid = thereIsCreditCardWithLimit(new BigDecimal(100)); //HTTP POST
        // when
        clientWantsToWithdraw(TEN, cardUUid); //HTTP GET
        // then
        thereIsOneWithdrawalOf(TEN, cardUUid);
    }
```

## 5.5. 암시적 동기화로 스프링 애플리케이션 이벤트가 있는 CQRS

코드는 [with-application-events](https://github.com/ddd-by-examples/all-things-cqrs/tree/master/with-application-events) 모듈에서 찾을 수 있습니다.

이벤트를 반환하는 불변 도메인 모듈이 있는 버전도 있습니다. [여기](https://github.com/ddd-by-examples/all-things-cqrs/tree/master/with-application-events-immutable) 에서 찾을 수 있습니다 .

앱 실행:

```
mvn spring-boot:run
```

샘플 *인출* 명령:

```
curl localhost:8080/withdrawals -X POST --header 'Content-Type: application/json' -d '{"card":"3a3e99f0-5ad9-47fa-961d-d75fab32ef0e", "amount": 10.00}' --verbose
```

쿼리로 확인:

```
curl http://localhost:8080/withdrawals?cardId=3a3e99f0-5ad9-47fa-961d-d75fab32ef0e --verbose
```

예상 결과:

```
[{"amount":10.00}]
```

아키텍처 개요:

[![행사](https://github.com/ddd-by-examples/all-things-cqrs/raw/master/appevents.jpeg)](https://github.com/ddd-by-examples/all-things-cqrs/blob/master/appevents.jpeg)

REST API에 대한 자동 E2E 테스트는 [여기](https://github.com/ddd-by-examples/all-things-cqrs/blob/master/with-application-events/src/test/java/io/dddbyexamples/cqrs/CommandQuerySynchronizationTest.java) 에서 찾을 수 있습니다 .

```
    @Test
    public void shouldSynchronizeQuerySideAfterSendingACommand() {
        // given
        UUID cardUUid = thereIsCreditCardWithLimit(new BigDecimal(100)); //HTTP POST
        // when
        clientWantsToWithdraw(TEN, cardUUid); //HTTP GET
        // then
        thereIsOneWithdrawalOf(TEN, cardUUid);
    }
```

## 5.6. 암시적 동기화로 트리거가 있는 CQRS

코드는 [트리거](https://github.com/ddd-by-examples/all-things-cqrs/tree/master/with-trigger) 모듈에서 찾을 수 있습니다.

앱 실행:

```
mvn spring-boot:run
```

샘플 *인출* 명령:

```
curl localhost:8080/withdrawals -X POST --header 'Content-Type: application/json' -d '{"card":"3a3e99f0-5ad9-47fa-961d-d75fab32ef0e", "amount": 10.00}' --verbose
```

쿼리로 확인:

```
curl http://localhost:8080/withdrawals?cardId=3a3e99f0-5ad9-47fa-961d-d75fab32ef0e --verbose
```

예상 결과:

```
[{"amount":10.00}]
```

아키텍처 개요:

[![방아쇠](https://github.com/ddd-by-examples/all-things-cqrs/raw/master/trigger.jpg)](https://github.com/ddd-by-examples/all-things-cqrs/blob/master/trigger.jpg)

REST API에 대한 자동 E2E 테스트는 [여기](https://github.com/ddd-by-examples/all-things-cqrs/blob/master/with-trigger/src/test/java/io/dddbyexamples/cqrs/CommandQuerySynchronizationTest.java) 에서 찾을 수 있습니다 .

```
    @Test
    public void shouldSynchronizeQuerySideAfterSendingACommand() {
        // given
        UUID cardUUid = thereIsCreditCardWithLimit(new BigDecimal(100)); //HTTP POST
        // when
        clientWantsToWithdraw(TEN, cardUUid); //HTTP GET
        // then
        thereIsOneWithdrawalOf(TEN, cardUUid);
    }
```

## 5.7. 동기화로 트랜잭션 로그 테일링이 있는 CQRS

데이터베이스 관리 시스템에서 수락한 트랜잭션의 로그인 데이터베이스의 [트랜잭션 로그](https://en.wikipedia.org/wiki/Transaction_log) 를 수신하여 동기화 합니다.

코드는 [with-log-tailing](https://github.com/ddd-by-examples/all-things-cqrs/tree/master/with-log-tailing) 모듈에서 찾을 수 있습니다.

추가 구성 요소:

- MySQL은 인출 및 신용 카드를 보관합니다.
- 데이터베이스 트랜잭션 로그(이 경우 MySQL)에서 읽은 메시지에 대한 pub/sub용 [Apache Kafka .](https://kafka.apache.org/)
- [Kafka 는 ](https://www.confluent.io/product/connectors/)[Debezium](https://debezium.io/) 과 연결 하여 MySQL의 트랜잭션 로그를 읽고 메시지를 Kafka의 주제로 스트리밍합니다.
- Kafka의 주제에서 메시지를 읽는 [Spring Cloud Stream .](https://cloud.spring.io/spring-cloud-stream/)

앱을 실행할 때 프로젝트의 **루트 에 있어야 합니다.**

- *docker-compose.yaml* 에서 서비스 *kafka* - 호스트 시스템과 일치하도록 IP를 **변경 합니다.** 9092를 가리키는 포트 유지:

```
ADVERTISED_LISTENERS=PLAINTEXT://YOUR_HOST_IP:9092
```

- 전체 인프라 실행:

```
docker-compose up
```

- Kafka Connect에 MySQL DB의 테일 트랜잭션 로그를 알리고 Kafka에 메시지를 보냅니다.

```
curl -i -X POST -H "Accept:application/json" -H  "Content-Type:application/json" http://localhost:8083/connectors/ -d @source.json --verbose
```

샘플 *인출* 명령:

```
curl localhost:8080/withdrawals -X POST --header 'Content-Type: application/json' -d '{"card":"3a3e99f0-5ad9-47fa-961d-d75fab32ef0e", "amount": 10.00}' --verbose
```

쿼리로 확인:

```
curl http://localhost:8080/withdrawals?cardId=3a3e99f0-5ad9-47fa-961d-d75fab32ef0e --verbose
```

예상 결과는 아래에서 볼 수 있습니다. 트랜잭션 로그를 읽고 출금을 생성하는 데 시간이 걸린다는 점을 기억하십시오. 따라서 인출는 즉시 확인되지 않을 수 있습니다.

```
[{"amount":10.00}]
```

아키텍처 개요:

[![로그테일링](https://github.com/ddd-by-examples/all-things-cqrs/raw/master/transactionlog.jpg)](https://github.com/ddd-by-examples/all-things-cqrs/blob/master/transactionlog.jpg)

트랜잭션 로그 테일링을 테스트하는 것은 문제가 있거나 불가능하기 때문에 명령 및 쿼리를 확인하는 E2E 테스트는 없습니다. 그러나 Kafka의 주제에 메시지가 도착하면 적절한 철회가 생성되는지 테스트할 수 있습니다. 코드는 [다음](https://github.com/ddd-by-examples/all-things-cqrs/blob/master/with-log-tailing/src/test/java/io/dddbyexamples/cqrs/sink/ReadModelUpdaterTest.java) 과 같습니다.

```
    @Test
    public void shouldSynchronizeQuerySideAfterLogTailing() {
        // given
        String cardUUid = thereIsCreditCardWithLimit(new BigDecimal(100));
        // when
        creditCardUpdateReadFromDbTransactionLog(TEN, cardUUid);
        // then
        thereIsOneWithdrawalOf(TEN, cardUUid);
    }
```

## 5.8. 도메인 이벤트를 동기화로 사용하는 CQRS

명령을 성공적으로 처리한 후 도메인 이벤트를 전송하여 동기화를 수행합니다.

코드는 [이벤트](https://github.com/ddd-by-examples/all-things-cqrs/tree/master/with-events) 모듈에서 찾을 수 있습니다. 2개의 추가 모듈이 있으며 아키텍처는 완전히 분산되어 있습니다. [소스](https://github.com/ddd-by-examples/all-things-cqrs/tree/master/with-events/with-events-source) (명령 처리)와 [싱크](https://github.com/ddd-by-examples/all-things-cqrs/tree/master/with-events/with-events-sink) (쿼리 처리)가 있습니다 .

추가 구성 요소:

- 신용 카드를 보관하는 H2 DB.
- 출금을 유지하는 [MongoDB .](https://www.mongodb.com/what-is-mongodb)
- Spring Data Reactive MongoDb는 Mongo와 반응적으로 대화합니다.
- 비차단 웹 서비스를 제공하는 [프로젝트 Reactor](http://projectreactor.io/)
- 도메인 이벤트의 게시/구독을 위한 [Apache Kafka](https://kafka.apache.org/)
- Kafka의 주제에서/로 메시지를 읽고 쓰기 위한 [Spring Cloud Stream .](https://cloud.spring.io/spring-cloud-stream/)

앱을 실행할 때 프로젝트의 **루트 에 있어야 합니다.**

- 전체 인프라 실행:

```
docker-compose up
```

샘플 *인출* 명령:

```
curl localhost:8080/withdrawals -X POST --header 'Content-Type: application/json' -d '{"card":"3a3e99f0-5ad9-47fa-961d-d75fab32ef0e", "amount": 10.00}' --verbose
```

쿼리로 확인(다른 포트 알림: **8888** !):

```
curl http://localhost:8888/withdrawals?cardId=3a3e99f0-5ad9-47fa-961d-d75fab32ef0e --verbose
```

예상 결과는 아래에서 볼 수 있습니다. Kafka에서 도메인 이벤트를 게시하고 읽는 데 시간이 걸립니다. 따라서 인출는 즉시 확인되지 않을 수 있습니다.

```
[{"amount":10.00}]
```

아키텍처 개요:

[![이벤트](https://github.com/ddd-by-examples/all-things-cqrs/raw/master/events.jpg)](https://github.com/ddd-by-examples/all-things-cqrs/blob/master/events.jpg)

하나의 테스트에서 2개의 마이크로 서비스를 테스트하는 것은 권장되지 않으므로 명령 및 쿼리를 확인하는 E2E 테스트는 없습니다. 그러나 Kafka의 주제에 메시지가 도착하면 적절한 철회가 생성되는지 테스트할 수 있습니다. 코드는 [다음](https://github.com/ddd-by-examples/all-things-cqrs/blob/master/with-events/with-events-sink/src/test/java/io/dddbyexamples/cqrs/sink/ReadModelUpdaterTest.java) 과 같습니다.

```
    @Test
    public void shouldSeeWithdrawalAfterGettingAnEvent() {
        //when
        anEventAboutWithdrawalCame(TEN, cardID);

        //then
        thereIsOneWithdrawalOf(TEN, cardID);
    }
```

또한 성공적인 철회 후에 적절한 도메인 이벤트 게시가 이어지는지 테스트하는 것도 가능합니다. 코드는 [여기](https://github.com/ddd-by-examples/all-things-cqrs/blob/master/with-events/with-events-source/src/test/java/io/dddbyexamples/cqrs/EventsPublishingTest.java) 에 있습니다 .

```
    @Test
    public void shouldEventuallySendAnEventAboutCardWithdrawal() throws IOException {
        // given
        UUID cardUUid = thereIsCreditCardWithLimit(new BigDecimal(100));
        // when
        clientWantsToWithdraw(TEN, cardUUid);
        // then
        await().atMost(FIVE_SECONDS).until(() -> eventAboutWithdrawalWasSent(TEN, cardUUid));
    }
```













- 서론
- Repository Pattern
- DIP 와 Repository
- DDD 관점의 Repository 는?

 

# 서론

 

Spring Data JPA 를 사용한다면 가장 많이 접하는 것이 바로 **Repository** 가 아닐까 싶다.

 

이 Repository 라는 개념은 JPA 에서 사용되는 인터페이스가 아니라 **Spring Data 에서 사용되는 인터페이스**이다.

 

우리는 특정 도메인 객체를 **지속적으로 사용하기 위해**서 (혹은 영속하기 위해) 애플리케이션을 지탱하는 Backing Service 에 영속해야 한다.

 

그게 RDBMS 나 NoSQL, 로컬에 존재하는 파일 혹은 remote 의 어딘가던 **물리적인 저장소**가 필요하다.

 

그래서 `spring-data-jpa`, `spring-data-jdbc`, `spring-data-elasticsearch` 를 막론하고 `repository` 를 통해서 **영속성 장치와 통신**을 하게 된다.

 

#### 이러한 Repository 는 사실 Spring 의 개념은 아니며 역시 Java나 어떤 구현 기술에 종속적인 이야기 역시도 아니다.

 

Spring-Data 모듈 역시 repository 라는 개념에 의해서 시작되었고 오늘은 그에 대해서 알아보려 한다.

 

# Repository Pattern

 

Repository Pattern 은 2004 년 Eric Evans 의 Domain-Driven-Design 에서 처음 소개된 개념으로, 공통적인 데이터 Access & Manipluate 에 집중하여 **도메인 모델 계층과 구현 기술을 분리**시키는 것을 의미한다.

 



![img](https://k.kakaocdn.net/dn/qVvS4/btrKl2Z0RFO/jAtPLDW6nEkdXsATqPtWd0/img.png)



 

이렇게 함으로써 RDB 나 Query 와 같이 어떠한 **구현 기술에 종속적이지 않고 도메인에 더욱 집중**할 수 있게 되는 패턴을 의미한다.

Repository 에 대해서 **Martin Fowler** 는 다음과 같이 표현한다.

 

> 
> A repository performs the tasks of an intermediary between the domain model layers and data mapping, acting in a similar way to a set of domain objects in memory. Client objects declaratively build queries and send them to the repositories for answers. Conceptually, a repository encapsulates a set of objects stored in the database and operations that can be performed on them, providing a way that is closer to the persistence layer. Repositories, also, support the purpose of separating, clearly and in one direction, the dependency between the work domain and the data allocation or mapping.

 

즉 한 문장으로 요약하자면 다음과 같다.

 

#### Mediates between the domain and data mapping layers using a collection-like interface for accessing domain objects.

 

즉, domain 과 data source layer 간에 **중재자 역할을 수행**하는 것이라고 한다.

 

repository 는 영속성 장치에서 쿼리의 결과로 받아온 데이터를 repository 에서는 domain 에서 사용하기 적합하도록 Domain 객체로 mapping 하는 역할을 수행한다.

 



![img](https://k.kakaocdn.net/dn/bBS65I/btrKn8FsgLb/HPd8CMu2jKyp4gaC1IWDH0/img.png)



 

위 그림은 Jpa 를 사용할 떄 기본으로 사용되는 Repository 의 구현체인 (정확히는 `JpaRepository` 의 구현체) `SimpleJpaRepository` 클래스이다.

 

위와 같이 entity 에 대한 정보를 받기도 하며 실제 connection 을 처리할 entity manager 또한 보유하고 있는 것을 알 수 있다.

 

## 꼭 영속성 장치일 필요는 없다.

 

domain 관점에서 보면 repository 뒤에 어떤 장치가 숨어있던 상관 없이 **데이터를 조작하는 데에 필요한 인터페이스만을 바라보고 협력**하기 때문에 RDBMS 이던, WebServer 이던, FileSystem 이던 **상관 없다**.

 

# DIP 와 Repository

 

**DDD** 에서 말하는 Layered Architecture 를 적용한다면 아마 다음과 같은 구조가 일반적으로 사용될 것이다.

 



![img](https://k.kakaocdn.net/dn/chOMx8/btrKFPHCiJe/ZWvipetWASVD2Mk5dKg500/img.png)



 

가운데 있는 Infrastructure Persistence Layer 가 바로 Repository 가 존재하는 레이어이다.

 

### 앞서서 왜 Repository 가 존재한다고 했는지 기억나는가?

 

다시 한번 말하자면 Repository 는 도메인을 영속화하는데 필요한 **일종의 명세**이다.

 

도메인 관점에서 **"나는 이런 것들을 이렇게 저장할 것이고 이렇게 불러올거야!"** 라는 명세를 만들어놓고 실제 구현 기술에 대한 부분을 분리시킨다는 것이다.

 

그래서 위의 **Domain Model Layer** 와 **Infrastructure Layer** 를 나누는 것도 같은 맥락이다.

 

- **Domain Model Layer** 에서는 저장하는 방법에 대해서 관심을 갖고,
- **Infrastructure Layer** 에서는 실제로 어떻게 저장하는지에 대해서 관심을 갖는다.

 

이 둘 사이를 어떻게 구현할까?

 

정답은 DIP 이다.

 

#### DIP 를 이용해서 도메인 모델에 존재하는 Repository 추상화로 만들고 실제 구현을 infrastructure 에서 하게 한다.

 

DIP 를 사용한다는 것은 **의존의 방향을 역전시**키겠다는 이야기다.

 

즉, **고수준 모듈**(의미 있는 단일 기능)이 **저수준 모듈**(고수준 모듈을 구현하기 위한 기능)에 의존하지 않도록 하기 위함인데, 단지 **선언과 구현을 분리** 쯤으로 생각한다면 잘못된 DIP 의 결과가 나올 수 있다.

 

예를 들면 아래와 같은 형태로 말이다.

 



![img](https://k.kakaocdn.net/dn/SkaLE/btrKFQs2D3D/WLIx5sayjEFs8IU1XbwzFk/img.png)



 

이렇게 된다면 Repository 를 다양한 형태의 구현으로 다형적이게 만든다는 조건은 만족시켰다.

 

하지만 여전히 고수준 모듈이 저수준 모듈에 의존하고 있다. 즉, 의존의 관점에서 본다면 `OrderDomainService` 가 infrastructure 를 알게 되는 형태이다.

 

> 이렇게 의존의 방향이 잘못된다면 많은 고통이 발생할 수 있다. 현재는 이상없는것 처럼 보이겠지만 한 해가 지나고, 다음 해가 지나서 다른 개발자가 도메인 로직에서 Repository 를 추상적인 것에 의존하는 게 아니라 구체적인 ElasticsearchRepository 를 의존했다고 해보자. 그리고 그 다음해에 비즈니스가 변경되어 저장할 필요 없이 단지 API 로 다른 곳에 relay 만 한다고 했을때, 이들을 분리하는 것은 또 다른 pain point 가 될 것이다.

 

그래서 이를 해결하기 위해서 `OrderRepository` 를 고수준 모듈로 만드는 것이다.

 



![img](https://k.kakaocdn.net/dn/bvzi8X/btrKHZhF5ku/gBCK8Pk8K6NayAP0MHVXw0/img.png)



 

이렇게 된다면 하나의 추상적인 Repository 에 대해서 다양한 구현이 가능하게 된다.

 



![img](https://k.kakaocdn.net/dn/p0yXX/btrKHxlqxpo/9B2ZJJDaCkpulP6a4AH9u0/img.png)



 

결국 **Repository 는 Jpa 진영에서 DB 와 연결하기 위한 layer 로 부르는 것이 아니라는 것**을 알 수 있다.

 

**도메인의 관점**에서 Repository 는 데이터를 저장하는 backing 을 추상화한 것으로 도메인은 어떻게 Repository 에 저장되는지 관심을 갖지 않는다.

 

오로지 도메인 로직 자체에만 관심을 갖는다.

 

그래서 도메인 관점으로 보자면 Repository 를 두고 infrastructure 에서 이를 JPA 를 사용하던 MyBatis 를 사용해서 DAO 계층을 만들건 **중요하지 않게 되는 것**이다.

 

------

# DDD 관점의 Repository 는?

 

이제 Domain Driven Design, 설계의 관점에서 Repository 를 생각해보자

 

DDD 에서는 애그리거트라는 용어가 존재한다.

 

애그리거트는 간략하게 말하자면 **하나의 unit, 비즈니스 단위로 취급할 수 있는 오브젝트의 집합**이다.

 

예를 들어서 **Review** 라는 애그리거트가 존재한다고 해보자.

 

그럼 해당 Review 라는 애그리거트에는 다음과 같은 오브젝트가 존재할 것이다.

 

- Review 에는 글을 쓴 사람인 `Reviewer`
- 글의 본문인 `Contents`
- 리뷰의 제목인 `Title`
- 해당 리뷰의 `Tag`

 

이외에도 리뷰를 표현하는 다양한 오브젝트가 존재할 것인데, 해당 오브젝트는 **리뷰**라는 비즈니스 **개념 하나**를 구성하는 요소들이다.

 

결국 어떤 애그리거트가 저장된다는 소리는 해당 애그리거트에 포함되는 모든 entity 와 value 들에 대해서 **transaction consistency** 를 보장해야 한다.

 

#### 그래서 일반적으로 DDD 에서는 하나의 Aggregate 를 Repository 의 대상 엔티티로 삼는다.

 

즉 Review 라는 애그리거트가 존재할 때, 해당 애그리거트를 저장하고 로드하는 Repository 는 ReviewRepository 만 존재해야 한다는 소리다.

 

Review 가 Tag 들을 포함하고 있다고 해서 TagRepository 가 존재해서는 안된다는 것이다.

 

> 이에 대해서 자세한 이야기는 해당 블로그의 [DDD 카테고리](https://wonit.tistory.com/category/🔬아키텍처/- Domain-Driven-Design) 의 Aggregate 와 AggregateRoot 에서 자세히 확인할 수 있다.

#  

# 끝으로

 

이렇게 오늘은 Repository 에 대해서 알아보았다.

 

최근에 DDD 를 학습하면서 오해를 풀게된 것이 Repository 라는 것은 개념인 것이고 어떠한 기술에 국한된 내용이 아니라는 것이다.

 

왜 Repository Pattern 이 중요하고 어떤 것들을 오해했는지 말하면서 다소 추상적인 이야기를 했을 수도 있다.

 

다음 시간에는 실제로 Spring 에서 Repository Pattern 을 구현하고, DIP 를 통해서 도메인이 추상적인 것에 의존하도록 만들어 볼 예정이다.



### 목차

- 서론
- 문제점 1. 복잡성
- 문제점 2. 확장성
- 해결해야 할 문제
- 결론

#  

# 서론

 

[지난 시간](https://wonit.tistory.com/636?category=955962) 우리는 DDD 에서 이야기하는 Repository Pattern 에 대해서 알아보았다.

 

 

지난 시간에 이야기했던 내용을 간단히 요약하면 다음과 같다.

 

#### 데이터를 persist 하고 load 하는 것을 Repository 라는 인터페이스로 추상화하여 domain layer 에서 실제 구현 기술에 대해서 모르게 한다

 

그렇다는 소리는 **Domain 과 Infrastructure 는 서로 코드베이스 상에서 격리시켜 느슨한 결합을 유지해야 한다는 것**이다.

 

이번 시간에는 지난 시간에 개념적으로만 설명했던 문제점들을 실제로 맞닥들이면서 Domain 과 Infrastructure 가 혼재된 코드는 어떤 문제가 있는지 알아볼 것이다.

 

그리고 Domain 과 Infrastructure 는 서로 코드베이스 상에서 격리시켜 느슨한 결합을 유지함으로써 이 문제를 해결할 것이다.

 

이렇게 함으로써 발생할 수 있는 여러 문제들도 확인해보고 내가 내린 결론을 이야기해보도록 하겠다.

 

먼저 문제점에 대해서 이야기해보자. 아래의 코드는 Order 라는 주문 객체 하나에 대한 정의이다.

 

```
@Entity(name = "orders")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    public static Order create(Long userId) {
        Long id = LongIdGenerator.gen();
        return new Order(id, userId, new ArrayList<>(), 0L);
    }

    public static Order by(Long id, Long userId, List<Long> orderItems, Long totalPrice) {
        return new Order(id, userId, orderItems, totalPrice);
    }

    @Id
    private Long id;
    private Long userId;
    @ElementCollection
    private List<Long> orderItems;
    private Long totalPrice;

    private Order(Long id, Long userId, List<Long> orderItems, Long totalPrice) {
        this.id = id;
        this.userId = userId;
        this.orderItems = orderItems;
        this.totalPrice = totalPrice;
    }

    public void add(Product product) {
        orderItems.add(product.getId());

        totalPrice += product.getPrice();
    }
    // more ...
}
```

 

위 코드에는 두가지 문제점이 크게 드러난다.

 

1. 복잡성
2. 확장성

 

하나씩 확인해보도록 하겠다.

 

# 문제점 1. 복잡성 (Domain 과 Infrastructure 가 혼재된 코드)

 

이게 무슨 말일까?

 

위 코드를 보면 **Domain 과 Infrastructure 가 혼재되어있어 복잡성이 느껴지는 코드**이다.

 

복잡성을 다른 표현으로 해보자면, Order 라는 **도메인이 가져야 할 비즈니스 로직과**, Order 객체를 **저장하기 위해서 필요한 코드**들이 함께 뒤섞여 있다는 것이다.

 

- 도메인 로직 : `add(Product product)` 메서드 등등
- 저장하기 위한 로직 : `@Entity`, `@Id`, `@ElementCollection` 과 여러 Builder, `AllArgsConstructor` 등등

 

비즈니스 로직이 `add(Product product)` 만 존재함에도 불구하고 비즈니스와 무관한 어노테이션이 덕지덕지 붙어있고 도메인 관점에서는 전혀 중요하지 않은 내용들이 섞여있다.

 

과연 도메인 관점에서 `@ElementCollection` 이라는 어노테이션이 중요할까? 주문의 관점에서 해당 객체가 어떤 Id 생성 전략을 갖는지가 중요할까?

 

전혀 중요하지 않다. 오히려 도메인에 무관한 내용이 있기 때문에 더욱 도메인에 집중할 수 없게 된다.

 

어떻게 해결할 수 있을까?

 

#### 답은 의외로 간단하다. 분리시키자

 

서로 다른 책임을 갖는 두가지 클래스로 분리하자! **하나는 도메인, 비즈니스 로직에 관심을 갖는 객체, 다른 하나는 저장에 관심갖는 객체로 분리하자**

 

### Order.java

```
@Getter
public class Order {

    public static Order create(Long userId) {
        Long id = LongIdGenerator.gen();
        return new Order(id, userId, new ArrayList<>(), 0L);
    }
    
    private final Long id;
    private final Long userId;
    private List<Long> orderItems;
    private Long totalPrice;

    private Order(Long id, Long userId, List<Long> orderItems, Long totalPrice) {
        this.id = id;
        this.userId = userId;
        this.orderItems = orderItems;
        this.totalPrice = totalPrice;
    }

    public void add(Product product) {
        orderItems.add(product.getId());

        totalPrice += product.getPrice();
    }
}
```

 

여전히 복잡해 보이지만 괜찮다. 훨씬 비즈니스적이고 도메인 다워졌다.

 

### SpringDataJpaOrderEntity.java

 

```
@Entity(name = "orders")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpringDataJpaOrderEntity {
    @Id
    private Long id;
    private Long userId;
    @ElementCollection
    private List<Long> orderItems;
    private Long totalPrice;
}
```

 

이제 순수하게 persist 와 관련된 코드만 남게 되었다.

 

우리는 Hibernate 를 사용하는 세대이기 때문에 Entity 라는 어노테이션 만으로도 쉽게 저장 대상 객체라는 것을 알릴 수 있다.

 

하지만 그렇지 않는 상황이라면? 걱정 없다. 더러워지는 것은 `SpringDataJpaOrderEntity` 뿐이니까.

 

> 다른 이야기이지만 이름도 중요하다! 왜 **SpringDataJpaOrderEntity** 라고 했을까? domain 의 infrastructure 구현체가 SpringDataJpa 이기 때문이다. Jpa 를 쓰니까 JpaOrderEntity 라고 한다? 이것도 적절하지 않다고 생각한다. jpa 와 spring data jpa 는 서로 다른 구현 기술이기 때문에 명확히 명시해야 한다.

#  

# 문제점 2. 확장성 (새로운 비기능적 요구사항 추가)

 

자, 이제 우리가 만들었던 비즈니스가 시장에서 가치를 인정받고 사용자들이 급격하게 많이 늘어났다고 가정해보자.

 

기존에 사용하던 기술은 일반적인 관계형 데이터베이스였는데, 만약 엄청나게 빠른 속도로 검색이 가능해야 한다고 해보자.

 

그리고 DB Latency 가 너무 심해서 쿼리 튜닝으로는 도저히 해결할 수 없는 상황이라고 굳이 굳이 가정해보자.

 

#### 결국, 팀의 합의 하에 MySQL 이 아니라 Elasticsearch 를 사용해야만 한다고 결정되었다.

 

그럼 처음 봤던 **Domain 과 Infrastructure 가 혼재된 코드라면 어떻게 해야할까?**

 

도메인은 역시 복잡하고 저장하는 Repository 는 아마 다음과 같을 것이다

 

```
public interface OrderRepository extends JpaRepository<Order, Long> {
    Order save(Order entity);
    Optional<Order> findByUserId(Long id);
}
```

 

이 상황에서 선택할 수 있는 선택지는 여러가지가 있지만, Spring Data 모듈을 사용하는 나는 아마도 `org.springframework.boot:spring-boot-starter-data-elasticsearch` 에서 제공하는 `ElasticsearchRepository` 를 사용할 것 같다.

 

```
public interface OrderRepository extends ElasticsearchRepository<Order, Long> {
    Order save(Order entity);
    Optional<Order> findByUserId(Long id);
}
```

 

괜찮을까? 괜찮을것이다.

 

하지만 이렇다면 어떨까?

 

`JpaRepository` 안에 `DeleteAllInBatch()` 라는 시그니처가 존재한다.

 



![img](https://k.kakaocdn.net/dn/5aq6F/btrNbWX56fT/kko4gK3oOZ5VUKtp66RrQ0/img.png)



 

해당 메서드가 무엇을 하는지는 모르지만 어딘가에서 저 메서드를 사용한다면 큰일이다. 왜냐? `ElasticsearchRepository` 에는 해당 시그니처가 없기 때문이다

 



![img](https://k.kakaocdn.net/dn/cl2obw/btrNb3JpfD2/jegRPoowKpImLGaa0wqt8K/img.png)



 

어떻게 해결할 수 있을까? 여러 방법이 있겠지만, 이번 주제의 컨텍스트로 이어가자면,

 

#### 역시 답은 의외로 간단하다. DIP 를 활용하면 된다

 

지난 시간에 소개했던 그림을 다시 가져와서 보자

 



![img](https://k.kakaocdn.net/dn/cYN3Do/btrM9RQANtv/myNLdfaCrrfmWwzyfCx1MK/img.png)



 

과 같은 형태로 구성하면 도메인과 인프라를 적절하게 떼어낼 수 있다.

 

앞서서 도메인과 영속성 객체를 분리했듯, Repository 역시 분리시키자

 

```
public interface OrderRepository {
    Order save(Order order);
    Optional<Order> findByUserId(long userId);
}
```

 

도메인은 위와 같이 도메인에 존재하는 Repository 는 Order 객체를 알도록 하고, 영속성 인프라 레포지토리를 영속성 엔티티와 함께 사용하면 된다.

 

```
public interface SpringDataJpaOrderRepository extends JpaRepository<SpringDataJpaOrderEntity, Long> {
    SpringDataJpaOrderEntity save(SpringDataJpaOrderEntity entity);
    Optional<SpringDataJpaOrderEntity> findByUserId(Long id);
}
```

 

그럼 아마 다음과 같은 형태가 될 것이다.

 



![img](https://k.kakaocdn.net/dn/bCOyrW/btrNdoFO5mk/wOx56CPBg0nQaafkOXUzGk/img.png)



 

하지만 한가지 문제가 생긴다.

 

#### 도메인에 존재하는 Repository 와 infrastructure 에 존재하는 Repository 의 형태가 서로 달라져버린다.

 

그래서 이 사이에 Adapter 를 하나 두고, 해당 Adapter 가 Domain 의 Repository 와 Infrastructure 의 Repository 사이의 규격을 맞춰주면 된다.



![img](https://k.kakaocdn.net/dn/dFjTa5/btrNa2qGVIq/8RGkTa7mfHdL1RCsa7iMhk/img.png)



코드로 보자면 다음과 같을 것이다.

```
@Component
@RequiredArgsConstructor
public class SpringDataJpaOrderRepositoryAdapter implements OrderRepository {

    private final SpringDataJpaOrderRepository repository;

    @Override
    public Order save(Order order) {
        SpringDataJpaOrderEntity entity = repository.save(convert(order));
        return convert(entity);
    }

    @Override
    public Optional<Order> findByUserId(long userId) {
        Optional<SpringDataJpaOrderEntity> optional = repository.findByUserId(userId);
        if (optional.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(convert(optional.get()));
    }

    private SpringDataJpaOrderEntity convert(Order domain) {
        return SpringDataJpaOrderEntity.builder()
                .id(domain.getId())
                .userId(domain.getUserId())
                .orderItems(domain.getOrderItems())
                .totalPrice(domain.getTotalPrice())
                .build();
    }

    private Order convert(SpringDataJpaOrderEntity entity) {
        return Order.by(entity.getId(),
                entity.getUserId(),
                entity.getOrderItems(),
                entity.getTotalPrice());
    }
}
```

 

해당 Adapter 를 Bean 으로 등록하고 도메인에 있는 Repository 를 사용할 때, Spring Context 에게 빈을 주입받아서 사용한다.

 

#### 그러면 사용하는 클라이언트는 Domain 의 Repository 을 사용하는 것이지만 실제로 저장은 Infrastructure 의 Repository 가 호출되어 저장될 것이다.

 

```
@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository repository;

    public Order order() {
        return repository.save(new Order());
    }

    public Order find() {
        return repository.findByUserId(1L);
    }
}
```

 

전체적으로 도메인과 인프라를 분리한 패키지의 모습을 확인해보자

 



![img](https://k.kakaocdn.net/dn/YTLrz/btrM9QqwVJj/ZlVkRzrkoMk70QRez0Wkgk/img.png)



 

도메인, 웹, 인프라스트럭처를 gradle 모듈로 분리시켜 의존의 제약을 걸어두었다.

 

> 다시한번 이야기하지만 위의 내용으로는 블로그 글의 한정된 특성으로 인해 이해가 어려울 수 있습니다. 자세한 사항은 [github repository-ddd](https://github.com/my-research/ddd/tree/master/repository-pattern) 에서 확인할 수 있습니다.

 

어떤가 이해가 조금 되는가?

 

분리를 함으로써 우리는 복잡성을 낮추고 확장성을 높이는 이점을 취할 수 있었다.

 

하지만 분리하는 것이 마냥 좋은 것만은 아니다.

 

몇가지 해결해야 할 문제점들이 있는데, 확인해보자

 

# 해결해야 할 문제

 

해결해야 할 문제들이 꽤나 있다.

 

1. **너무 많은 컨버팅 코드**
2. **휴먼 에러**
3. **JPA 사용시 Lazy Loading 불가**

##  

## 너무 많은 컨버팅 코드

 

우선 위 adapter 코드를 보면 알 수 있듯이, 너무나도 많은 컨버팅이 필요하다.

 

만약 하나의 애그리거트에 매우 많은 중첩 객체가 존재한다면 어떻게될까?

 



![img](https://k.kakaocdn.net/dn/Sb0OH/btrM9QqvZ0T/tEGyAhvIsVOhd4VoAoD1H0/img.png)



 

컨버팅을 하느라 엄청난 시간을 쏟게 될 것이다.

 

내 경험상 이는 코드가 하드웨어로 가는 지름길인 **변경의 두려움** 이라는 매우 좋지 않은 시그널을 주더라.

 

## 휴먼 에러

 

위의 컨버팅 코드와 직결된 내용인데, 아래의 코드에서 문제점을 찾아보자.

 

```
@Getter
public class Order {
    public static Order by(Long id, Long userId, List<Long> orderItems, Long totalPrice) {
        return new Order(id, userId, orderItems, totalPrice, "");
    }

    private final Long id;
    private final Long userId;
    private List<Long> orderItems;
    private Long totalPrice;
    private final String address;

    public Order(Long id, Long userId, List<Long> orderItems, Long totalPrice, String address) {
        this.id = id;
        this.userId = userId;
        this.orderItems = orderItems;
        this.totalPrice = totalPrice;
        this.address = address;
    }
    // 생략
}
```

 

위는 도메인 Order 이고 아래는 인프라스트럭처의 Order 이다.

 

```
@Entity(name = "orders")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpringDataJpaOrderEntity {
    @Id
    private Long id;
    private Long userId;
    @ElementCollection
    private List<Long> orderItems;
    private Long totalPrice;
}
```

 

문제점을 찾을 수 있는가?

 

#### 문제는 Order 객체에 address 라는 필드가 추가되었지만 누군가의 실수로 인해서 Entity 에는 address 가 없다.

 

이는 어쩌면 당연하겠지만 명시적으로 혹은 코드상에서 Domain 과 Infra 의 연결이 분리되었기 때문에 발생하는 문제이다.

 

이를 컴파일 단에서 확인할 수 없으니 그만큼 안정성은 떨어질 수 있다.

 

## JPA 사용시 Lazy Loading 불가

 

역시 Converting 의 연장선이다.

 

잘 알다싶이 Jpa 는 Lazy Loading 이라는 기술이 존재하고 간단히 이야기하자면 **실제 사용이 있을 때만 쿼리를 날리는 일종의 성능 전략**이다.

 

하지만 컨버팅을 하는 과정에서 실제 참조가 이뤄지기 때문에 Lazy Loading 자체가 사라지게 된다.

 

이를 해결하기 위해서는 이에 특화된 Proxy 를 직접 만들어서 사용해야 한다고도 하더라.

 

**하지만 Aggregate 에 대해서 Lazy Loading 이 필요하지 않다고 보는 의견도 존재한다.**

 

현재 번역 작업중인 [cqrs-journey](https://github.com/dhslrl321/cqrs-journey-korean-ver/blob/master/part01-journey/journey03/06. Write Model.mdwn) 한글 번역에 비슷한 이야기가 나온다.

 

A 개발자와 B 개발자가 이야기를 나누던 중 다음과 같은 말을 한다.

> I agree. I have found that lazy-loading in the command side means I have it mod- eled wrong. If I don’t need the value in the command side, then it shouldn’t be there.

즉 lazy loading 이라는 것이 필요하다는 것은 애그리거트의 설계가 잘못되었을 가능성이 존재한다. 

 

aggregate 에 value 가 필요하면 한번에 load 되어야 하고 필요하지 않으면 load 되지 않아야 한다는 이야기다.

# 결론

 

결론을 이야기할 때가 되었다.

 

운이 좋게도 참여한 프로젝트 중에서 도메인을 분리했던 프로젝트가 있고 분리하지 않았던 프로젝트가 있다.

 

#### 도메인을 분리했던 프로젝트에서는 정말 도메인을 도메인답게 사용할 수 있더라

 

처음 프로젝트 진행 기간중 3/4 을 fully 도메인에 집중하고 1/4 의 기간동안만 실제 구현에 대한 고민을 하고 코드를 쳐내려갔다.

 

도메인을 개발할 때는 전혀 성능과 DB 테이블의 필드, 칼럼을 고려하지 않았다.

 

단점을 보자면 인프라를 구현할 때 역시 convert 하는데 많은 시간을 소비했고, 여러 DB 의 제약 조건 (이를테면 낙관적 잠금으로 인한 Version 에 대한 처리) 때문에 너무나 비합리적인 코드도 존재하기도 했었다.

 

하지만 실제 구현도 하드코드되어도 문제가 없었다. 성능이 좋지 않아도 문제가 없다.

 

DIP 를 해뒀기 때문에 언제든지 변경할 수 있는 자신이 있기 때문이다.

 

또한 Command Side 와 Query Side 의 분리가 있는 CQRS 도 크게 문제 없다. command 던 query 던 시작은 도메인이다

 

#### 도메인을 분리하지 않았던 프로젝트에서는 빠른 속도감이 관건이었다.

 

최범균님의 `도메인 주도 개발 시작하기: DDD 핵심 개념 정리부터 구현까지` 에서는 '실제로 DB 구현 기술이 바뀌는 일은 실무에서 거의 없다' 라고까지 표현한다.

 

사실 최근에도 회사에서 DB 구현 기술이 바뀌는 경험을 했기에 위의 말에 100% 공감을 하지는 않지만 몇가지 구현 기술에 대한 어노테이션이 침투하지만 뭐 어떤가?

 

우리는 어댑터라는 것을 알고 있고 멋진 다른 방법들도 무수히 많고 역시 언제나 답이 있을 것이다.

 

구현 기술을 도메인에서 걷어내는 속도와 처음부터 분리시켜서 작업을 진행하는 것 사이에서 적절한 고민이 필요해 보인다.

 

#### 그래서 무조건적인 Domain 과 구현 기술을 분리해야해! 분리하면 좋고 분리하지 않으면 안좋아 라는 이분법적인 사고는 좋지 않다.

 

적절한 기술과 상황을 고려하자











# CQRS 2

------

## 도입

 

회사 시스템이 전통적인 CRUD 애플리케이션에서 Event 기반의 시스템으로 바뀌어 가는 과정에 팀에 합류를 하게 되어 나의 최근 가장 큰 관심사가 바로 이 CQRS 이다.

 

학부 시절에도 마이크로서비스를 공부하며 잠깐 잠깐 봤던 CQRS 는 이름 부터 생소하기에 겁을 먹었던 기억이 난다.

 

하지만 CQRS 의 원리 자체는 사실 되게 간단하다

 

Command 와 Query 를 분리하자!

 

Command 와 Query 를 먼저 정의하고 이야기를 계속 해보자

 

## Query 와 Command 란?

 

Query 와 Command 에 대해서 이야기 하기 위해서는 CQS 에 대해서 먼저 알아볼 필요가 있다.

 



![img](https://k.kakaocdn.net/dn/noW3z/btrDJvHoMQF/LhmGWyrsiLzjtiQ6YLS0uK/img.png)



 

CQS 는 Design By Contract 라는 용어를 만든 버트란드 메이어, Betrand Meyer 가 소개한 개념이다.

 

함수는 특정 동작을 수행하는 코드 블록을 의미하는데, 함수의 목적에 따라서 두가지로 분류할 수 있다.

 

그것이 바로 Command 와 Query 이다.

 

## Command

 

Command 는 시스템에 어떠한 side effect, 즉 **변경을 가하는 행위**를 하는 것을 말한다.

 

그래서 Command 성 함수 라고 한다면 변경을 가하는 함수를 말할 수 있다.

 

#### Command 성 함수는 시스템의 상태를 변경시키는 대신 값을 반환하지 않아야 한다.

 

```
// O, 상태만 변경시킴
void updateUser(User user) {
  user.updateAge(12);
}

// X, 값을 반환
User updateUser(User user) {
  return user.updateAge(12);
}
```

##  

## Query

 

이에 반해서 Query 는 시스템의 **상태를 관찰할 수 있는 행위**를 하는 것을 말한다.

 

마찬가지로 Query 성 함수라고 한다면 **단지 시스템의 상태만 확인하는 함수**라고 할 수 있다.

 

#### Query 성 함수는 시스템의 상태를 단지 반환하기만 하고 상태를 변경시키지 않아야 한다.

 

```
// O, 값만 반환
User getUser(Long userId) {
  return users.get(userId);
}

// X, 상태를 변경
void getUser(Long userId) {
  User user = users.get(userId);
  user.updateLastQueriedAt();
  return user;
}
```



## CQS 는 Command Query Separation 이다

 



![img](https://k.kakaocdn.net/dn/bJSJmY/btrDInQo2qL/Kld8bSqz9kUAD7k7OK1Ivk/img.png)



 

버트란드 마이어는 위의 Command 와 Query 를 분리해야 하며 하나의 함수는 이 성격을 띄어야 한다고 했다.

 

#### 즉, 어떠한 함수가 있다면 그 함수는 Command 또는 Query 중 하나의 역할만 수행해야 한다.

 

만약 하나의 함수에서 Command 와 Query 가 모두 동시에 일어나게 된다면, 이는 소프트웨어의 3가지 원칙 중 복잡하지 않아야 한다는 KISS 가 지켜지지 않을 것이다.

 

이런 관점에서 연장선상에 있는 것이 바로 Command Query Responsibility Segregation 이다.



##  CQRS 란?



![img](https://k.kakaocdn.net/dn/biJw1F/btrPjHRlLLx/KJ50YLuVHEmV90hp0CWsJk/img.png)



 

CQRS 는 [Greg Young](https://twitter.com/gregyoung) 이 소개한 말이고, CQS에 비해 조금 더 큰 레벨에서의 Command 모듈과 Query 모듈의 책임을 분리하자는 말이다.

 

CQS 는 코드 레벨에서의 분리를 말한다면 CQRS 는 조금 더 거시적인 관점에서의 분리를 의미한다.

 

------

 

앞서 버트란드 메이어가 말한것 처럼 command 형이거나 query 형의 함수를 분리시키면 소프트웨어가 더욱 단순해지고 이해하기 쉬워진다고 했다.

 

**CQRS 는 이 원칙을 차용한다.**

 

아래의 그림은 [CQRS Journey Guide 한글 번역](https://github.com/dhslrl321/cqrs-journey-korean-ver/blob/master/part02-references/reference02/01. CQRS 가 무엇인가.mdwn) 에서 가져온 그림이다.

 

일반적으로 CQRS 패턴을 적용한 애플리케이션은 다음과 같은 형태를 띄게 된다.

 



![img](https://k.kakaocdn.net/dn/HA7Ma/btrPjG5Yh4C/wNFBqHZMBV11llAHtKC7TK/img.png)



 

위 그림을 보면 하나의 service interface 를 두고 두개의 서로 다른 애플리케이션이 존재한다.

 

1. **read side**
2. **write side**

##  

## 한가지 예를 들며 이야기해보자.

 

아래의 그림은 **게임 보드** 라는 가상의 도메인을 모델링한 그림이다.

 



![img](https://k.kakaocdn.net/dn/cSJIIJ/btrDKJ6C9ru/fvSGrHMiOXnDuAZbyfjNs1/img.png)



- 사용자는 정답을 입력한다.
- 정답이라면 점수를 올리고 오답이면 점수를 내린다.
- 사용자의 랭킹 확인할 수 있다.

 

위의 구조는 동일한 도메인 모델을 사용한다. 즉, 조회의 책임과 명령의 책임이 하나의 도메인에 포함되어있다는 이야기다.

 

## 그럼 무슨 문제가 생길까?

 

위 아키텍처에서는 3가지의 잠재적인 문제가 존재할 것이다.

 

1. 복잡성
2. 성능
3. 확장성

###  

### 문제1. 복잡성, 도메인이 비대해진다.

 

우리의 시스템에서 도메인이 갖는 의미에 대해서 생각해보자.

 

**도메인이란 곧 비즈니스이다.**

 

비즈니스는 보통 특정한 데이터의 상태를 변경 (create, update, delete) 을 하는 것이다. 이러한 비즈니스는 시간이 증가하면서 점점 복잡도가 올라가게 되고, 많은 요구사항들을 포함할 수 있어야 했다.

 

하지만 query 는 어떠할까?

 

query 는 단순 데이터 조회이기 때문에 비즈니스와 무관하지만 가끔 query 를 위한 처리가 도메인에 침투하는 경우가 생긴다.

 

우리가 만나는 UI 의 데이터 대부분은 비정형 데이터들일 것이다. 즉, 쇼핑몰에서 user 정보에 따른 관심 물품, 최근 구매 내역, AI 추천 상품 목록 등 이러한 데이터는 하나 이상의 바운디드 컨텍스트와 관련이 있게 된다.

 

그럼 이런 비즈니스 요구사항이 생길때마다 도메인을 수정해야 할까? 쿼리를 더 잘 할 수 있도록 도메인에 관련 행위를 추가해야 하는가?

 

안 그래도 도메인 자체는 비대해져 가는데, 비즈니스 자체를 표현해야 하는 도메인에 query 가 침투한다? 즉 복잡성이 올라간다는 것을 의미한다.

 

### 문제2. 성능

 

대부분의 write 연산에서 우리는 일관성 (consistency) 에 대해서 많은 신경을 써야한다.

 

대부분의 잘 알려진 consistency 를 지키기 위한 해법 으로 **DB Locking 기법**을 사용할 것이다.

 

write 연산에서 한번 lock 을 잡게 되면 그 뒤의 read 연산이 모두 대기를 하게 되며 전반적인 성능이 낮아지는 결과를 초래할 수 있다. (물론 lock 의 기법에 따라서 결과는 다를 수 있지만)

 

### 문제 3. 확장성

 

많은 시스템에서 읽기와 쓰기에 대한 불균형이 존재한다는 사실은 꽤나 자주 들리는 이야기다.

 

쓰기 작업과 읽기 작업의 비율이 **1(write):1000(read)** 라고 한다.

 

그렇다는 이야기는 **read side 와 write side 의 서버는 서로 다른 기준으로 설계**가 되어야 한다는 것이다.

 

즉, 독립적으로 확장이 가능해야 하고 각각 목적에 맞는 다른 솔루션이 필요하다는 이야기다.

 

만약 이 둘이 분리되어 있지 않고 하나의 컴퓨팅 엔진만을 사용한다면 혹은 하나의 데이터소스만을 사용한다면 독립적인 확장이 힘들 것이다.

 

------

 

하지만 여기서 CQRS 를 적용해서 **책임에 따른 Command 와 Query 의 연산을 각각 독립적으로 분리**시키면 다음과 같은 형태를 띄게 된다.

 



![img](https://k.kakaocdn.net/dn/4jbbd/btrDGI1XXCx/2zNozrFdIOdh89p49LLFVk/img.png)



 

앞서 보았던 일반적인 CQRS 의 형태와 비슷하게 되었다.

 

그렇다면 정답과 관련된 비즈니스를 책임지는 윗쪽 도메인에게는 **상태를 변경시키는 Command 의 책임**만 존재하기에 비즈니스를 그대로 표현할 수 있다.

 

역시 아래의 도메인에게는 **상태를 확인하는 Query 의 책임** 만 존재하게 된다.

 

이렇게 되면 어떤 장점이 있을 수 있을까?

 

단순히 가장 먼저 드는 생각은 Command 와 Query 에 각기 다른 Persistence Module 을 사용할 수 있을것이다.

 

- Command Side 에는 객체 중심적인 개발이 가능한 **JPA** 를 사용할 수 있다.
- Query Side 에는 최적화된 쿼리를 위해서 **MyBatis** 를 사용할 수 있을 것이다.

##  

## CQRS 더 고도화 시켜볼 수 있다

 

Command 와 Query 의 책임이 분리되었기 때문에 Command 와 Query 는 서로 다른 인프라가 구성될 수 있다.

 



![img](https://k.kakaocdn.net/dn/51uPK/btrDKJ6Edvt/YH8Rcwh5XlxSR7EkalKkDK/img.png)



 

그럼 위와 같이 Polygrat 한 Persistance Infra 가 구성될 수 있다.

 

그럼 또 아래와 같이 구성할 수 있다.

 

- Command infra 에는 write 에 최적화된 DB를 사용할 수 있을 것이다.
- Query Side 에는 더욱 빠른 쿼리을 위해서 elasticsearch나 opensearch 와 같은 검색 엔진을 도입할 수 있을 것이다.

 

그래서 보통 Query Side 에 **Materialized View** 를 이용하여 복잡한 쿼리를 방지하고 **관점에 따른 정보 뷰**를 생성하여 사용하곤 한다

 

그리고 Write Side 에서 발생하는 변경 사항들에 대해서는 중간에 메시징 인프라를 이용해서 계속해서 동기화를 시켜주는 형태로 사용하기도 한다.

 

> 조금 더 고도화된 CQRS 패턴 구현법 대한 자세한 이야기는 [CQRS Journey guide 한글 번역](https://github.com/dhslrl321/cqrs-journey-guide-korean) 에서 확인할 수 있다.

#  

# CQRS 의 장점과 단점

 

위에서 우리는 CQRS 에 대해서 대략적으로 알아보았고, **level 별로 CQRS 를 구분해보았다**.

 

CQRS 라는 것은 구현하는 방법에 따라서 복잡성이 천차만별이다. 꼭 read side 와 write side 를 메시징 인프라로 연결하지 않더라도 구현할 수 있듯 사용하는 목적과 용례가 다르다.

 

이제 CQRS 에 대한 장단점을 한번 생각해보자

 

## 장점

- 도메인 로직에만 집중할 수 있게 된다
  - Command 와 Query 를 분리했기 때문에 OCP 를 준수하는 도메인 모델을 만들 수 있다.
  - 이를 통해 결국 도메인 로직에 비즈니스 로직을 집중시킬 수 있다
- 데이터소스의 독립적인 크기 조정이 가능하다
  - 보통 read 와 write 의 비율은 1000 : 1 이다.
  - 그러므로 write db 가 물리적으로 나뉘어져 있다면 해당 db 인스턴스는 작게 유지하고 read db 인스턴스에 더 높은 투자를 할 수 있다.
- 단순한 쿼리
  - Query side 에서는 Materialized View 를 이용할 수 있는데, 이를 통해서 복잡한 조인 쿼리 없이 단순한 쿼리를 이용해서 원하는 정보를 얻어올 수 있다

##  

## 단점

- 복잡성이 올라간다
  - command side 와 query side 를 명시적으로 분리하기 때문에 복잡성이 올라간다.
- 즉시적인 일관성이 보장되지 않는다
  - command 에 따른 data 의 무결성이 잠시동안 깨질 수 있다.
  - 이 말은 데이터의 consistency 가 항상 동일하지 않다
  - 하지만 최종적으로는 데이터가 맞춰질 것이니 **Eventual Consistency**라고 할 수 있다.

> CQRS 패턴의 장단점에 대한 자세한 이야기는 [CQRS Journey guide 한글 번역](https://github.com/dhslrl321/cqrs-journey-guide-korean) 에서 확인할 수 있다.

 

이런 장단점을 가지고 있기 때문에 **도메인 또는 비즈니스 규칙이 단순한 곳**에서는 CQRS 를 하기 힘들다.

 

하지만 많은 사용자가 동시에 동일한 데이터에 **병렬로 접근**하는 경우나 read 연산이 write 연산보다 많은 경우는 CQRS 를 사용하는 것이 효과적이다.

 

# **마치며**

 

위에서 보았듯 CQRS 자체는 되게 간단하지만 마이크로소프트 문서를 포함하여 여러 곳에서 CQRS 를 설명할 때, 오해에 여지가 있게 설명을 하고 있었다.

 

**CQRS 는 사실 데이터소스와 크게 연관이 있지 않다.**

 

말 그대로 CQRS 는 Command 와 Query 를 분리시키는 것이다.

 

하지만 여러 곳에서 CQRS 와 Datasource 를 엮어서 혹은 Event sourcing 과 엮어서 설명을 하고 있었기에 초반에 많은 오해를 했었다..

------









# [별첨]

# 클린코드 

## Class

### 클래스 체계

JAVA Convention에 따르면 가장 먼저 변수 목록이 나온다.
**static public --> static private --> private 인스턴스 --> (public은 필요한 경우가 거의 없다)**  
변수목록 다음에는 공개 함수가 나온다. 비공개 함수는 자신을 호출 하는 공개 함수 직후에 나온다.  
즉, 추상화 단계가 순차적으로 내려간다.

#### 캡슐화

변수와 유틸리티 함수는 가능한 공개하지 않는 편이 낫지만 반드시 숨겨야 하는 것은 아니다.  
우리에게 테스트는 중요하므로 테스트를 위해 protected로 선언해서 접근을 허용하기도 한다.  
**하지만 비공개 상태를 유지할 온갖 방법을 강구하고, 캡슐화를 풀어주는 결정은 언제나 최후의 수단이다.**

### 클래스는 작아야 한다!

클래스는 첫째! 작아야한다. 둘째! 작아야한다. 더 작아야 한다. 단 함수와는 다르게(함수는 물리적인 행 수로 측정)  
**클래스는 맡은 책임을 측정한다.**

### 개념은 빈 행으로 분리하라

코드의 각 줄은 수식이나 절을 나타내고, 여러 줄의 묶음은 완결된 생각 하나를 표현한다.  
생각 사이에는 빈 행을 넣어 분리해야한다. 그렇지 않다면 단지 줄바꿈만 다를 뿐인데도 코드 가독성이 현저히 떨어진다.

```java
// 어마어마하게 큰 슈퍼 만능 클래스
public class SuperDashboard extends JFrame implements MetaDataUser {
	public String getCustomizerLanguagePath()
	public void setSystemConfigPath(String systemConfigPath) 
	public String getSystemConfigDocument()
	public void setSystemConfigDocument(String systemConfigDocument) 
	public boolean getGuruState()
	public boolean getNoviceState()
	public boolean getOpenSourceState()
	public void showObject(MetaObject object) 
	public void showProgress(String s)
	public boolean isMetadataDirty()
	public void setIsMetadataDirty(boolean isMetadataDirty)
	public Component getLastFocusedComponent()
	public void setLastFocused(Component lastFocused)
	public void setMouseSelectState(boolean isMouseSelected) 
	public boolean isMouseSelected()
	public LanguageManager getLanguageManager()
	public Project getProject()
	public Project getFirstProject()
	public Project getLastProject()
	public String getNewProjectName()
	public void setComponentSizes(Dimension dim)
	public String getCurrentDir()
	public void setCurrentDir(String newDir)
	public void updateStatus(int dotPos, int markPos)
	public Class[] getDataBaseClasses()
	public MetadataFeeder getMetadataFeeder()
	public void addProject(Project project)
	public boolean setCurrentProject(Project project)
	public boolean removeProject(Project project)
	public MetaProjectHeader getProgramMetadata()
	public void resetDashboard()
	public Project loadProject(String fileName, String projectName)
	public void setCanSaveMetadata(boolean canSave)
	public MetaObject getSelectedObject()
	public void deselectObjects()
	public void setProject(Project project)
	public void editorAction(String actionName, ActionEvent event) 
	public void setMode(int mode)
	public FileManager getFileManager()
	public void setFileManager(FileManager fileManager)
	public ConfigManager getConfigManager()
	public void setConfigManager(ConfigManager configManager) 
	public ClassLoader getClassLoader()
	public void setClassLoader(ClassLoader classLoader)
	public Properties getProps()
	public String getUserHome()
	public String getBaseDir()
	public int getMajorVersionNumber()
	public int getMinorVersionNumber()
	public int getBuildNumber()
	public MetaObject pasting(MetaObject target, MetaObject pasted, MetaProject project)
	public void processMenuItems(MetaObject metaObject)
	public void processMenuSeparators(MetaObject metaObject) 
	public void processTabPages(MetaObject metaObject)
	public void processPlacement(MetaObject object)
	public void processCreateLayout(MetaObject object)
	public void updateDisplayLayer(MetaObject object, int layerIndex) 
	public void propertyEditedRepaint(MetaObject object)
	public void processDeleteObject(MetaObject object)
	public boolean getAttachedToDesigner()
	public void processProjectChangedState(boolean hasProjectChanged) 
	public void processObjectNameChanged(MetaObject object)
	public void runProject()
	public void setAçowDragging(boolean allowDragging) 
	public boolean allowDragging()
	public boolean isCustomizing()
	public void setTitle(String title)
	public IdeMenuBar getIdeMenuBar()
	public void showHelper(MetaObject metaObject, String propertyName) 
	
	// ... many non-public methods follow ...
}
```

```java
// 메소드를 5개로 줄인다고 하더라도 여전히 책임이 많다..

public class SuperDashboard extends JFrame implements MetaDataUser {
	public Component getLastFocusedComponent()
	public void setLastFocused(Component lastFocused)
	public int getMajorVersionNumber()
	public int getMinorVersionNumber()
	public int getBuildNumber() 
}
```

클래스 이름은 해당 클래스 책임을 기술해야된다. 작명은 클래스 크기를 줄이는 첫번째 관문임.  
간결한 이름이 떠오르지 않는다면 클래스 책임이 너무 많아서이다.  
(e.g. Chapter 2장에 언급한 것 처럼 Manager, Processor, Super 등)

또한 클래스 설명은 "if", "and", "or", "but"을 사용하지 않고 25 단어 내외로 가능해야된다.
한글의 경우 만약, 그리고, ~하며, 하지만 이 들어가면 안된다.

### 단일 책임의 원칙 - Single Responsibility Principle

단일 책임의 원칙 (이하 SRP)은 클래스나 모듈을 변경할 이유가 단 하나뿐이어야 한다는 원칙이다.
책임, 즉 변경할 이유를 파악하려고 애쓰다 보면 코드를 추상화 하기도 쉬워진다.  

```java
// 이 코드는 작아보이지만, 변경할 이유가 2가지이다.

public class SuperDashboard extends JFrame implements MetaDataUser {
	public Component getLastFocusedComponent()
	public void setLastFocused(Component lastFocused)
	public int getMajorVersionNumber()
	public int getMinorVersionNumber()
	public int getBuildNumber() 
}
```

```java
// 위 코드에서 버전 정보를 다루는 메서드 3개를 따로 빼서
// Version이라는 독자적인 클래스를 만들어 다른 곳에서 재사용하기 쉬워졌다.

public class Version {
	public int getMajorVersionNumber() 
	public int getMinorVersionNumber() 
	public int getBuildNumber()
}
```

SRP는 객체지향설계에서 더욱 중요한 개념이고, 지키기 수월한 개념인데, 개발자가 가장 무시하는 규칙 중 하나이다.  
대부분의 프로그래머들이 **돌아가는 소프트웨어**에 초점을 맞춘다. 전적으로 올바른 태도이기는 하지만,  
돌아가는 소프트웨어가 작성되면 **깨끗하고 체계적인 소프트웨어**라는 다음 관심사로 전환을 해야한다.

작은 클래스가 많은 시스템이든, 큰 클래스가 몇 개뿐인 시스템이든 돌아가는 부품은 그 수가 비슷하다.

> "도구 상자를 어떻게 관리하고 싶은가?  
> 작은 서랍을 많이 두고 기능과 이름이 명확한 컴포넌트를 나눠 넣고 싶은가?  
> 아니면 큰 서랍 몇개를 두고 모두 던져 넣고 싶은가?"  

**큰 클래스 몇개가 아니라 작은 클래스 여럿으로 이뤄진 시스템이 더 바람직하다.  
작은 클래스는 각자 맡은 책임이 하나며, 변경할 이유가 하나며, 다른 작은 클래스와 협력해  
시스템에 필요한 동작을 수행한다.** 

### 응집도

클래스는 인스턴스 변수 수가 작아야 한다.  
각 클래스 메서드는 클래스 인스턴스 변수를 하나 이상 사용해야 한다.  
일반적으로 메서드가 변수를 더 많이 사용할 수록 메서드와 클래스는 응집도가 더 높다.  
모든 인스턴스 변수를 메서드마다 사용하는 클래스는 응집도가 가장 높지만, 이런 클래스는 가능하지도,  
바람직하지도 않다. 하지만 가능한한 응집도가 높은 클래스를 지향해야 한다.  
**응집도가 높다는 말은 클래스에 속한 메서드와 변수가 서로 의존하며 논리적인 단위로 묶인다는 의미기 때문이다**

```java
// Stack을 구현한 코드, 응집도가 높은 편이다.

public class Stack {
	private int topOfStack = 0;
	List<Integer> elements = new LinkedList<Integer>();

	public int size() { 
		return topOfStack;
	}

	public void push(int element) { 
		topOfStack++; 
		elements.add(element);
	}
	
	public int pop() throws PoppedWhenEmpty { 
		if (topOfStack == 0)
			throw new PoppedWhenEmpty();
		int element = elements.get(--topOfStack); 
		elements.remove(topOfStack);
		return element;
	}
}
```

**함수를 작게, 매개변수 목록을 짧게**라는 전략을 따르다 보면  
때때로 몇몇 메서드만이 사용하는 인스턴스 변수가 아주 많아진다.  
이는 십중 팔구 새로운 클래스를 쪼개야 한다는 신호다.  
응집도가 높아지도록 변수와 메서드를 적절히 분리해 새로운 클래스 두세 개로 쪼개준다.

#### 응집도를 유지하면 작은 클래스 여럿이 나온다.

큰 함수를 작은 함수 여럿으로 나누기만 해도 클래스 수가 많아진다.
예를 들어,   
변수가 아주 많은 큰 함수가 하나 있다  
--> 큰 함수 일부를 작은 함수로 빼내고 싶다   
--> 빼내려는 코드가 큰 함수에 정의 된 변수를 많이 사용한다  
--> 변수들을 새 함수에 인수로 넘겨야 하나? NO!  
--> 변수들을 클래스 인스턴스 변수로 승격 시키면 인수가 필요없다. But! 응집력이 낮아짐  
--> **몇몇 함수가 몇몇 인스턴스 변수만 사용한다면 독자적인 클래스로 분리해도 된다!**

큰 함수를 작은 함수 여럿으로 쪼개다 보면 종종 작은 클래스 여럿으로 쪼갤 기회가 생긴다.

```java
// 이 하나의 크고 더러운 함수를 여러 함수와 클래스로 잘게 나누면서 적절한 이름을 부여해보자!

package literatePrimes;

public class PrintPrimes {
	public static void main(String[] args) {
		final int M = 1000; 
		final int RR = 50;
		final int CC = 4;
		final int WW = 10;
		final int ORDMAX = 30; 
		int P[] = new int[M + 1]; 
		int PAGENUMBER;
		int PAGEOFFSET; 
		int ROWOFFSET; 
		int C;
		int J;
		int K;
		boolean JPRIME;
		int ORD;
		int SQUARE;
		int N;
		int MULT[] = new int[ORDMAX + 1];
		
		J = 1;
		K = 1; 
		P[1] = 2; 
		ORD = 2; 
		SQUARE = 9;
	
		while (K < M) { 
			do {
				J = J + 2;
				if (J == SQUARE) {
					ORD = ORD + 1;
					SQUARE = P[ORD] * P[ORD]; 
					MULT[ORD - 1] = J;
				}
				N = 2;
				JPRIME = true;
				while (N < ORD && JPRIME) {
					while (MULT[N] < J)
						MULT[N] = MULT[N] + P[N] + P[N];
					if (MULT[N] == J) 
						JPRIME = false;
					N = N + 1; 
				}
			} while (!JPRIME); 
			K = K + 1;
			P[K] = J;
		} 
		{
			PAGENUMBER = 1; 
			PAGEOFFSET = 1;
			while (PAGEOFFSET <= M) {
				System.out.println("The First " + M + " Prime Numbers --- Page " + PAGENUMBER);
				System.out.println("");
				for (ROWOFFSET = PAGEOFFSET; ROWOFFSET < PAGEOFFSET + RR; ROWOFFSET++) {
					for (C = 0; C < CC;C++)
						if (ROWOFFSET + C * RR <= M)
							System.out.format("%10d", P[ROWOFFSET + C * RR]); 
					System.out.println("");
				}
				System.out.println("\f"); PAGENUMBER = PAGENUMBER + 1; PAGEOFFSET = PAGEOFFSET + RR * CC;
			}
		}
	}
}
```

위 코드를... 바꿔보자면

```java
package literatePrimes;

public class PrimePrinter {
	public static void main(String[] args) {
		final int NUMBER_OF_PRIMES = 1000;
		int[] primes = PrimeGenerator.generate(NUMBER_OF_PRIMES);
		
		final int ROWS_PER_PAGE = 50; 
		final int COLUMNS_PER_PAGE = 4; 
		RowColumnPagePrinter tablePrinter = 
			new RowColumnPagePrinter(ROWS_PER_PAGE, 
						COLUMNS_PER_PAGE, 
						"The First " + NUMBER_OF_PRIMES + " Prime Numbers");
		tablePrinter.print(primes); 
	}
}
```

```java
package literatePrimes;

import java.io.PrintStream;

public class RowColumnPagePrinter { 
	private int rowsPerPage;
	private int columnsPerPage; 
	private int numbersPerPage; 
	private String pageHeader; 
	private PrintStream printStream;
	
	public RowColumnPagePrinter(int rowsPerPage, int columnsPerPage, String pageHeader) { 
		this.rowsPerPage = rowsPerPage;
		this.columnsPerPage = columnsPerPage; 
		this.pageHeader = pageHeader;
		numbersPerPage = rowsPerPage * columnsPerPage; 
		printStream = System.out;
	}
	
	public void print(int data[]) { 
		int pageNumber = 1;
		for (int firstIndexOnPage = 0 ; 
			firstIndexOnPage < data.length ; 
			firstIndexOnPage += numbersPerPage) { 
			int lastIndexOnPage =  Math.min(firstIndexOnPage + numbersPerPage - 1, data.length - 1);
			printPageHeader(pageHeader, pageNumber); 
			printPage(firstIndexOnPage, lastIndexOnPage, data); 
			printStream.println("\f");
			pageNumber++;
		} 
	}
	
	private void printPage(int firstIndexOnPage, int lastIndexOnPage, int[] data) { 
		int firstIndexOfLastRowOnPage =
		firstIndexOnPage + rowsPerPage - 1;
		for (int firstIndexInRow = firstIndexOnPage ; 
			firstIndexInRow <= firstIndexOfLastRowOnPage ;
			firstIndexInRow++) { 
			printRow(firstIndexInRow, lastIndexOnPage, data); 
			printStream.println("");
		} 
	}
	
	private void printRow(int firstIndexInRow, int lastIndexOnPage, int[] data) {
		for (int column = 0; column < columnsPerPage; column++) {
			int index = firstIndexInRow + column * rowsPerPage; 
			if (index <= lastIndexOnPage)
				printStream.format("%10d", data[index]); 
		}
	}

	private void printPageHeader(String pageHeader, int pageNumber) {
		printStream.println(pageHeader + " --- Page " + pageNumber);
		printStream.println(""); 
	}
		
	public void setOutput(PrintStream printStream) { 
		this.printStream = printStream;
	} 
}
```

```java
package literatePrimes;

import java.util.ArrayList;

public class PrimeGenerator {
	private static int[] primes;
	private static ArrayList<Integer> multiplesOfPrimeFactors;

	protected static int[] generate(int n) {
		primes = new int[n];
		multiplesOfPrimeFactors = new ArrayList<Integer>(); 
		set2AsFirstPrime(); 
		checkOddNumbersForSubsequentPrimes();
		return primes; 
	}

	private static void set2AsFirstPrime() { 
		primes[0] = 2; 
		multiplesOfPrimeFactors.add(2);
	}
	
	private static void checkOddNumbersForSubsequentPrimes() { 
		int primeIndex = 1;
		for (int candidate = 3 ; primeIndex < primes.length ; candidate += 2) { 
			if (isPrime(candidate))
				primes[primeIndex++] = candidate; 
		}
	}

	private static boolean isPrime(int candidate) {
		if (isLeastRelevantMultipleOfNextLargerPrimeFactor(candidate)) {
			multiplesOfPrimeFactors.add(candidate);
			return false; 
		}
		return isNotMultipleOfAnyPreviousPrimeFactor(candidate); 
	}

	private static boolean isLeastRelevantMultipleOfNextLargerPrimeFactor(int candidate) {
		int nextLargerPrimeFactor = primes[multiplesOfPrimeFactors.size()];
		int leastRelevantMultiple = nextLargerPrimeFactor * nextLargerPrimeFactor; 
		return candidate == leastRelevantMultiple;
	}
	
	private static boolean isNotMultipleOfAnyPreviousPrimeFactor(int candidate) {
		for (int n = 1; n < multiplesOfPrimeFactors.size(); n++) {
			if (isMultipleOfNthPrimeFactor(candidate, n)) 
				return false;
		}
		return true; 
	}
	
	private static boolean isMultipleOfNthPrimeFactor(int candidate, int n) {
		return candidate == smallestOddNthMultipleNotLessThanCandidate(candidate, n);
	}
	
	private static int smallestOddNthMultipleNotLessThanCandidate(int candidate, int n) {
		int multiple = multiplesOfPrimeFactors.get(n); 
		while (multiple < candidate)
			multiple += 2 * primes[n]; 
		multiplesOfPrimeFactors.set(n, multiple); 
		return multiple;
	} 
}
```

가장 먼저 원래 프로그램의 정확한 동작을 검증하는 테스트 슈트를 작성하라.  
그 다음 한번에 하나씩 여러번에 걸쳐 코드를 변경하고,  
코드를 변경 할 때 마다 테스트를 수행해 원래 프로그램과 동일하게 동작하는지 확인하라.

### 변경하기 쉬운 클래스 

시스템은 변경이 불가피하다. 그리고 변경이 있을 때 마다 의도대로 동작하지 않을 위험이 따른다.  
깨끗한 시스템은 클래스를 체계적으로 관리해 변경에 따르는 위험을 최대한 낮춘다.  

```java
// 해당 코드는 새로운 SQL문을 지원할 때 손대야 하고, 기존 SQL문을 수정할 때도 손대야 하므로 SRP위반

public class Sql {
	public Sql(String table, Column[] columns)
	public String create()
	public String insert(Object[] fields)
	public String selectAll()
	public String findByKey(String keyColumn, String keyValue)
	public String select(Column column, String pattern)
	public String select(Criteria criteria)
	public String preparedInsert()
	private String columnList(Column[] columns)
	private String valuesList(Object[] fields, final Column[] columns) private String selectWithCriteria(String criteria)
	private String placeholderList(Column[] columns)
}
```

클래스 일부에서만 사용되는 비공개 메서드는 코드 개선의 잠재적인 여지를 시사한다.

```java
// 공개 인터페이스를 전부 SQL 클래스에서 파생하는 클래스로 만들고, 비공개 메서드는 해당 클래스로 옮기고,
// 공통된 인터페이스는 따로 클래스로 뺐다.
// 이렇게 하면 update문 추가 시에 기존의 클래스를 건드릴 이유가 없어진다.

	abstract public class Sql {
		public Sql(String table, Column[] columns) 
		abstract public String generate();
	}
	public class CreateSql extends Sql {
		public CreateSql(String table, Column[] columns) 
		@Override public String generate()
	}
	
	public class SelectSql extends Sql {
		public SelectSql(String table, Column[] columns) 
		@Override public String generate()
	}
	
	public class InsertSql extends Sql {
		public InsertSql(String table, Column[] columns, Object[] fields) 
		@Override public String generate()
		private String valuesList(Object[] fields, final Column[] columns)
	}
	
	public class SelectWithCriteriaSql extends Sql { 
		public SelectWithCriteriaSql(
		String table, Column[] columns, Criteria criteria) 
		@Override public String generate()
	}
	
	public class SelectWithMatchSql extends Sql { 
		public SelectWithMatchSql(String table, Column[] columns, Column column, String pattern) 
		@Override public String generate()
	}
	
	public class FindByKeySql extends Sql public FindByKeySql(
		String table, Column[] columns, String keyColumn, String keyValue) 
		@Override public String generate()
	}
	
	public class PreparedInsertSql extends Sql {
		public PreparedInsertSql(String table, Column[] columns) 
		@Override public String generate() {
		private String placeholderList(Column[] columns)
	}
	
	public class Where {
		public Where(String criteria) public String generate()
	}
	
	public class ColumnList {
		public ColumnList(Column[] columns) public String generate()
	}
```

**잘 짜여진 시스템은 추가와 수정에 있어서 건드릴 코드가 최소이다.**

##### 변경으로부터 격리

OOP입문에서 concrete 클래스와 abstract 클래스가 있는데, 
concrete 클래스에 의존(상세한 구현에 의존)하는 클라이언트 클래스는 구현이 바뀌면 위험에 빠진다.  
그래서 인터페이스와 abstract 클래스를 사용해 구현이 미치는 영향을 격리시켜야 한다.  

상세한 구현에 의존하는 코드는 테스트가 어려움.  
그래서 추상화를 통해 테스트가 가능할 정도로 시스템의 결합도를 낮춤으로써  
유연성과 재사용성도 더욱 높아진다.

결함도가 낮다는 말은 각 시스템 요소가 다른 요소로부터 그리고 변경으로부터 잘 격리되어있다는 뜻이다.

```java
// Portfolio 클래스를 구현하자, 그런데 이 클래스는 외부 TokyoStockExchange API를 사용해 포트폴리오 값을 계산한다.
// 따라서 API 특성 상 시세 변화에 영향을 많이 받아 5분마다 값이 달라지는데, 이때문에 테스트 코드를 짜기 쉽지 않다.
// 그러므로 Portfolio에서 외부 API를 직접 호출하는 대신 StockExchange라는 인터페이스를 생성한 후 메서드를 선언하다.

public interface StockExchange { 
	Money currentPrice(String symbol);
}
```

```java
// 이후 StockExchange 인터페이스를 구현하는 TokyoStockExchange 클래스를 구현한다.
// 그리고 Portfolio 생성자를 수정해 StockExchange 참조자를 인수로 받는다.

public Portfolio {
	private StockExchange exchange;
	public Portfolio(StockExchange exchange) {
		this.exchange = exchange; 
	}
	// ... 
}
```

```java
// 이제 TokyoStockExchange 클래스를 흉내내는 테스트용 클래스를 만들 수 있다.(FixedStockExchangeStub)
// 테스트용 클래스는 StockExchange 인터페이스를 구현하며 고정된 주가를 반환한다.
// 그럼으로써 무난히 테스트 코드를 작성 할 수 있다.

public class PortfolioTest {
	private FixedStockExchangeStub exchange;
	private Portfolio portfolio;
	
	@Before
	protected void setUp() throws Exception {
		exchange = new FixedStockExchangeStub(); 
		exchange.fix("MSFT", 100);
		portfolio = new Portfolio(exchange);
	}

	@Test
	public void GivenFiveMSFTTotalShouldBe500() throws Exception {
		portfolio.add(5, "MSFT");
		Assert.assertEquals(500, portfolio.value()); 
	}
}

```

위에서 개선한 Portfolio 클래스는 상세 구현 클래스가 아닌 StockExchange라는 인터페이스에 의존하므로,  
실제로 주가를 얻어오는 출처나 얻어오는 방식 등과 같은 구체적인 사실을 모두 숨길 수 있다.



## Method

### 명령과 조회를 분리하라

함수는 뭔가 객체 상태를 변경하거나, 객체 정보를 반환하거나 둘 중 하나다. 둘 다 수행해서는 안 된다.  
`public boolean set(String attribute, String value);`같은 경우에는 속성 값 설정 성공 시 true를 반환하므로 괴상한 코드가 작성된다.  
`if(set(“username”, “unclebob”))...` 그러므로 명령과 조회를 분리해 혼란을 주지 않도록 한다.  

### 오류코드보다 예외를 사용하라!

try/catch를 사용하면 오류 처리 코드가 원래 코드에서 분리되므로 코드가 깔끔해 진다.

#### Try/Catch 블록 뽑아내기  

```java
if (deletePage(page) == E_OK) {
	if (registry.deleteReference(page.name) == E_OK) {
		if (configKeys.deleteKey(page.name.makeKey()) == E_OK) {
			logger.log("page deleted");
		} else {
			logger.log("configKey not deleted");
		}
	} else {
		logger.log("deleteReference from registry failed"); 
	} 
} else {
	logger.log("delete failed"); return E_ERROR;
}
```

정상 작동과 오류 처리 동작을 뒤섞는 추한 구조이므로 if/else와 마찬가지로 블록을 별도 함수로 뽑아내는 편이 좋다.

```java
public void delete(Page page) {
	try {
		deletePageAndAllReferences(page);
  	} catch (Exception e) {
  		logError(e);
  	}
}

private void deletePageAndAllReferences(Page page) throws Exception { 
	deletePage(page);
	registry.deleteReference(page.name); 
	configKeys.deleteKey(page.name.makeKey());
}

private void logError(Exception e) { 
	logger.log(e.getMessage());
}
```

오류 처리도 한가지 작업이다.

Error.java 의존성 자석

```java
public enum Error { 
	OK,
	INVALID,
	NO_SUCH,
	LOCKED,
	OUT_OF_RESOURCES, 	
	WAITING_FOR_EVENT;
}
```

오류를 처리하는 곳곳에서 오류코드를 사용한다면 enum class를 쓰게 되는데 이런 클래스는 의존성 자석이므로, 새 오류코드를 추가하거나 변경할 때 코스트가 많이 필요하다.
그러므로 예외를 사용하는 것이 더 안전하다.

 

