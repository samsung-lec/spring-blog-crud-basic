# 스프링 riverpod server

## 1. base64 사진 전송
- data:image/jpeg;base64,
- 프론트에서 base64 Strign 문자열 앞에 위에 글자 콤마까지 붙여서 전송해야 함.
- 서버는 해당 문자열 받아서 mimetype 파싱 후 file 저장하고, db에 경로 저장함
- get요청시에는 file의 path만 응답해주면 됨

## 2. 게시글 북마크, 북마크 개수, 북마크 목록

## 3. 채팅 기능 필요 (firestore 연동 or WebSocket 연동)

## 4. API 문서 만들기

## 5. 플러터 쪽 마무리 하기 (다대다 채팅 화면, 북마크 기능 화면, 사진 업로드, 사진 보기 화면)