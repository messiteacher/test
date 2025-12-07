# 📘 Public Holiday API Service

외부 공공 API(Nager API)를 이용하여 국가 정보 / 공휴일 정보를 관리하는 Spring Boot 기반 REST API 서비스입니다.  
H2 인메모리 DB이므로 클론 후 즉시 실행할 수 있습니다.

---

## 🚀 실행 방법

### 1) 프로젝트 클론

```bash
git clone https://github.com/messiteacher/test.git
cd test
```
2) 실행
```
./gradlew bootRun
```
✔ Java 21 필요
✔ 추가 설정 없음 (H2 인메모리 DB 자동 사용)


## 🌍 Country API Summary
✔ 국가 목록 조회

GET /countries

응답 예시:
```
[
  { "id": 1, "countryCode": "KR", "name": "Korea" },
  { "id": 2, "countryCode": "US", "name": "United States" }
]
```

✔ 국가 정보 로딩

POST /countries/load

Nager API에서 국가 목록을 가져와 DB에 저장합니다.

응답:
```
로딩 완료!
```

## 🎉 Holiday API Summary
✔ 특정 국가 공휴일 로딩

POST /holidays/load?countryCode=KR&year=2024

응답:
```
로딩 완료!
```

✔ 공휴일 검색

GET /holidays/search

파라미터:
`countryCode`, `name`, `page`, `size`

예시:
```
/holidays/search?countryCode=KR&page=0&size=10
```

응답 예시:
```
{
  "content": [
    {
      "date": "2024-01-01",
      "localName": "새해",
      "name": "New Year's Day",
      "countryCode": "KR",
      "fixed": true,
      "global": true
    }
  ],
  "totalElements": 12
}
```

✔ 공휴일 갱신(Refresh)
```
POST /holidays/refresh?countryCode=KR&year=2024
```

응답 예시:
```
[
  {
    "date": "2024-01-01",
    "name": "New Year's Day",
    "countryCode": "KR"
  }
]
```
✔ 공휴일 삭제
```
DELETE /holidays/{year}/{countryCode}
```
예시:
```
/holidays/2024/KR
```

응답:
```
삭제 완료!
```
⏱ 스케줄러 수동 실행 API
```
POST /scheduler/manual?countryCode=KR&year=2025
```
응답:
```
{
  "status": "OK",
  "message": "Scheduler executed manually.",
  "year": 2025,
  "countryCode": "KR"
}
```

## 📄 Swagger / OpenAPI 문서

Swagger UI
👉 http://localhost:8080/swagger-ui/index.html

## 💡 기술 스택

- Java 21

- Spring Boot 3.4

- Spring Data JPA

- H2 Database

- Springdoc OpenAPI(Swagger)

- Gradle

## ✨ 주요 기능 요약

- 국가/공휴일 정보 로딩

- 공휴일 검색 및 페이징

- 연도별 공휴일 갱신

- 데이터 삭제 API

- 자동 배치 스케줄러 + 수동 실행 API

- Swagger 기반 문서 자동화
