# Book-Management-System

## 구현 내용

- Restful API ( Resource, HTTP Method, Self Description )
- 회원 가입
- 회원 로그인 (jwt)
- 회원 조회
- 도서 등록, 수정, 삭제
- 도서 대여, 반납
- 도서 조회, 검색
- 대여 목록 조회
- 도서 관리 내역 조회, 검색
- 프론트엔드 구현 X
---

## URI
### Users API 


메소드 | 경로 | 설명 | 
|----- | ----------- | ------- | 
| POST |  /api/users | 회원 등록 |
| POST |  /api/users/authentication | 로그인 |
| GET |  /api/users/{id} | 회원 조회 |


### Books API 

메소드 | 경로 | 설명 | 
|----- | ----------- | ------- | 
| POST |  /api/books | 도서 등록 |
| PUT |  /api/books/{id} | 도서 수정 |
| DELETE |  /api/books/{id} | 도서 삭제 |
| PUT |  /api/books/{id}/rent | 도서 대여 |
| PUT |  /api/books/{id}/giveBack | 도서 반납 |
| GET |  /api/books/{id} | 도서 조회 |
| GET |  /api/books | 도서 검색 queryString |
| GET |  /api/books/users/{id} | 사용자가 대여한 도서 목록 조회 |


### Histories API 


메소드 | 경로 | 설명 | 
|----- | ----------- | ------- | 
| GET |  /api/histories | 도서 내역 검색 queryString |
| GET |  /api/histories/{id} | 도서 내역 조회 |

## 개발 환경

- OS - Windows 10
- IDE - Intelli J
- Language - Java 11
- Framework - Spring boot 2.7.10
- Database - mariadb,(H2)

#
