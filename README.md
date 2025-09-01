MVVM 패턴 실전 연습.

Repository 비교 학습을 위해 해당 프로젝트에는 Repository 생략!!

API 링크 :
* API 생성 : https://mocki.io/
* User : https://mocki.io/v1/7316a8a2-e5fa-462a-9fc0-2478c51429b9
* Animal : https://mocki.io/v1/27ffd0e4-eb7e-45c6-90fe-f5a2858a0082

1)@Volatile private val INSTANCE: AppDatabase? = null

Volatile는 앱 전체에서 INSTANCE객체를 하나만 쓰도록 보장


2)synchronized는 잠금장치 역할을 한다.
