# Trello Team Project

---
## 👨‍💻 Period : 2024/12/23 ~ 2024/12/31

---

## 👨‍💻 API명세서

---

### UserController

<details>
<summary>1. 유저 생성 (POST "/users")</summary>

- 요청
    - **Headers**:
        - Content-Type: application/json
    - **Body**:

  ```json
  {
      "email": "qwer@test.com",
      "password": "QweR1234@",
      "name":"홍길동",
      "role": "admin"
  }
  ```

    - 설명:

  | # | 이름      | 타입     | 설명       | Required |
    |---|---------|--------|----------|----------|
  | 1 | email   | String | 유저의 이메일  | O        |
  | 2 | password | String | 유저의 비밀번호 | O        |
  | 3 | name | String | 유저의 이름   | O        |
  | 4 | role | String | 유저의 권한   | O        |
</details>

---
### LoginController

<details>
<summary>1. 유저 로그인 (POST "/users/login")</summary>

- 요청
    - **Headers**:
        - Content-Type: application/json
    - **Body**:

  ```json
  {
      "email": "qwer@test.com",
      "password": "QweR1234@"
  }
  ```

    - 설명:

  | # | 이름      | 타입     | 설명       | Required |
      |---|---------|--------|----------|----------|
  | 1 | email   | String | 유저의 이메일  | O        |
  | 2 | password | String | 유저의 비밀번호 | O        |
</details>

<details>
<summary>2. 회원 탈퇴 (POST "/users")</summary>

- 요청
    - **Headers**:
        - Content-Type: application/json
    - **Body**:

  ```json
  {
      "password": "QweR1234@"
  }
  ```

    - 설명:

  | # | 이름      | 타입     | 설명       | Required |
        |---|---------|--------|----------|----------|
  | 1 | password | String | 유저의 비밀번호 | O        |
</details>

---
### WorkspaceController

<details>
<summary>1. 워크스페이스 생성 (POST "/workspaces")</summary>

- 요청
    - **Headers**:
        - Content-Type: application/json
    - **Path Variables**:
        - {workspaceId}: 수정할 워크스페이스 ID
        - **Body**:

  ```json
  {
      "title": "워크스페이스 제목1",
      "content": "내용"
  }
  ```

    - 설명:

  | # | 이름       | 타입     | 설명            | Required |
          |---|----------|--------|---------------|----------|
  | 1 | title    | String | 생성할 워크스페이스 제목 | O        |
  | 2 | content | String | 생성할 워크스페이스 내용 | X        |
-
</details>

<details>
<summary>2. 워크스페이스 조회 (GET "/workspaces/{workspaceId}")</summary>

- 요청
    - **Headers**:
        - Content-Type: application/json
    - **Path Variables**:
      - {workspaceId}: 조회할 워크스페이스 ID 

</details>

<details>
<summary>3. 워크스페이스 수정 (PATCH "/workspaces/{workspaceId}")</summary>

- 요청
    - **Headers**:
        - Content-Type: application/json
    - **Path Variables**:
        - {workspaceId}: 수정할 워크스페이스 ID
      - **Body**:

  ```json
  {
      "title": "워크스페이스 제목1",
      "content": "내용"
  }
  ```

    - 설명:

  | # | 이름       | 타입     | 설명            | Required |
        |---|----------|--------|---------------|----------|
  | 1 | title    | String | 수정할 워크스페이스 제목 | O        |
  | 2 | content | String | 수정할 워크스페이스 내용 | X        |
-
</details>

<details>
<summary>4. 워크스페이스 삭제 (DELETE "/workspaces/{workspaceId}")</summary>

- 요청
    - **Headers**:
        - Content-Type: application/json
    - **Path Variables**:
        - {workspaceId}: 삭제할 워크스페이스 ID
</details>

---
### BoardController

<details>
<summary>1. 보드 생성 (POST "/workspaces/{workspace_id}/boards")</summary>

- 요청
    - **Headers**:
        - Content-Type: application/json
    - **Part**:

  ```json
  {
    "title": "보드 제목1"
  }
  ```
  file
  - "background": "red.png"

    - 설명:

  | # | 이름          | 타입     | 설명        | Required |
        |---|-------------|--------|-----------|----------|
  | 1 | title        | String | 보드의 이름    | O        |
  | 2 | background | String | 보드의 배경 사진 | X        |

</details>

<details>
<summary>2. 보드 단건 조회 (GET "/boards/{board_id}")</summary>

- 요청
    - **Headers**:
        - Content-Type: application/json
    - **Path Variables**:
        - {board_id}: 조회할 보드 ID

</details>

<details>
<summary>3. 보드 단건 조회 (GET "/workspaces/{workspace_id}/boards/{board_id}")</summary>

- 요청
    - **Headers**:
        - Content-Type: application/json
    - **Path Variables**:
        - {workspace_id}: 조회할 워크스페이스 ID
        - {board_id}: 조회할 보드 ID

</details>

<details>
<summary>4. 보드 수정 (PATCH "/workspaces/{workspace_id}/boards/{board_id}")</summary>

- 요청
    - **Headers**:
        - Content-Type: application/json
    - **Path Variables**:
        - {workspace_id}: 조회할 워크스페이스 ID
        - {board_id}: 조회할 보드 ID
    - **Part**:

  ```json
  {
    "title": "보드 제목1"
  }
  ```
  file
    - "background": "red.png"

        - 설명:

  | # | 이름          | 타입     | 설명        | Required |
          |---|-------------|--------|-----------|----------|
  | 1 | title        | String | 보드의 이름    | O        |
  | 2 | background | String | 보드의 배경 사진 | X        |
-
</details>

<details>
<summary>5. 보드 삭제 (DELETE "/workspaces/{workspace_id}/boards/{board_id}")</summary>

- 요청
    - **Headers**:
        - Content-Type: application/json
    - **Path Variables**:
        - {workspace_id}: 삭제할 워크스페이스 ID
        - {board_id}: 삭제할 보드 ID
</details>

---
### ListController

<details>
<summary>1. 리스트 생성 (POST "/workspaces/{workspace_id}/boards/{board_id}/lists")</summary>

- 요청
    - **Headers**:
        - Content-Type: application/json
    - **Path Variables**:
        - {workspace_id}: 해당 워크스페이스 ID
        - {board_id}: 해당 보드 ID
    -  **Body**

  ```json
  {
      "title": "리스트 제목1"
  }
  ```

        - 설명:

  | # | 이름          | 타입     | 설명      | Required |
          |---|-------------|--------|---------|----------|
  | 1 | title        | String | 리스트의 이름 | O        |

</details>

<details>
<summary>2. 리스트 수정 (PATCH "/workspaces/{workspace_id}/boards/{board_id}/lists/{lists_id}")</summary>

- 요청
    - **Headers**:
        - Content-Type: application/json
    - **Path Variables**:
        - {workspace_id}: 해당 워크스페이스 ID
        - {board_id}: 해당 보드 ID
        - {lists_id}: 수정할 리스트 ID
    -  **Body**

  ```json
  {
      "title": "리스트 제목1",
      "order": 1
  }
  ```

        - 설명:

  | # | 이름          | 타입     | 설명      | Required |
            |---|-------------|--------|---------|----------|
  | 1 | title        | String | 리스트의 이름 | O        |
  | 2 | order        | Long   | 리스트의 순서 | O        |

</details>

<details>
<summary>3. 리스트 삭제 (DELETE "/workspaces/{workspace_id}/boards/{board_id}/lists/{list_id}")</summary>

- 요청
    - **Headers**:
        - Content-Type: application/json
    - **Path Variables**:
        - {workspace_id}: 삭제할 워크스페이스 ID
        - {board_id}: 삭제할 보드 ID
        - {lists_id}: 삭제할 리스트 ID
</details>

---
### CardController

<details>
<summary>1. 카드 생성 (POST "/workspaces/{workspace_id}/cards")</summary>

- 요청
    - **Headers**:
        - Content-Type: application/json
    - **Path Variables**:
        - {workspace_id}: 해당 워크스페이스 ID
    - **Part**:

  ```json
  {
      "title": "카드 제목1",
      "explanation": "설명",
      "deadline": "2024-12-25T20:00",
      "manager": "곽덕두"
  }
  ```
  file
    - "file": "red.png"

        - 설명:

  | # | 이름          | 타입            | 설명        | Required | 
  |---|-------------|---------------|-----------|----------|
    | 1 | title        | String        | 카드의 이름    | O        |
    | 2 | explanation | String        | 카드의 설명    | O        |
    | 2 | deadline | LocalDateTime | 데드라인      | O        |
    | 2 | manager | String        | 카드의 메니저   | O        |
    | 2 | file | File          | 카드의 첨부 파일 | X        |

</details>

<details>
<summary>2. 카드 단건 조회 (GET "/cards/{id}")</summary>

- 요청
    - **Headers**:
        - Content-Type: application/json
    - **Path Variables**:
        - {id}: 조회할 카드 ID

</details>

<details>
<summary>3. 카드 검색 (GET "/cards")</summary>

- 요청
    - **Headers**:
        - Content-Type: application/json
    - **Request Params**:
        - "boardId" : 1 (required = false)
        - "title" : "카드 제목" (required = false)
        - "content" : "카드 내용" (required = false)
        - "deadline" : "2024-12-25T00:00:00" (required = false)
        - "manager" : "곽덕두" (required = false)

</details>

<details>
<summary>4. 카드 수정 (PATCH "/workspaces/{workspace_id}/cards/{card_id}")</summary>

- 요청
    - **Headers**:
        - Content-Type: application/json
    - **Path Variables**:
        - {workspace_id}: 해당 워크스페이스 ID
        - {card_id}: 수정할 카드 ID
    - **Part**:

  ```json
  {
      "title": "카드 제목1",
      "explanation": "설명",
      "deadline": "2024-12-25T20:00",
      "manager": "곽덕두"
  }
  ```
  file
    - "file": "red.png"

        - 설명:

  | # | 이름          | 타입            | 설명        | Required | 
    |---|-------------|---------------|-----------|----------|
  | 1 | title        | String        | 카드의 이름    | O        |
  | 2 | explanation | String        | 카드의 설명    | O        |
  | 2 | deadline | LocalDateTime | 데드라인      | O        |
  | 2 | manager | String        | 카드의 메니저   | O        |
  | 2 | file | File          | 카드의 첨부 파일 | X        |

</details>

<details>
<summary>5. 카드 삭제 (DELETE "/workspaces/{workspace_id}/cards/{card_id}")</summary>

- 요청
    - **Headers**:
        - Content-Type: application/json
    - **Path Variables**:
        - {workspace_id}: 삭제할 워크스페이스 ID
        - {card_id}: 삭제할 카드 ID
</details>

---
### CommentController

<details>
<summary>1. 댓글 생성 (POST "cards/{cardId}/comments")</summary>

- 요청
    - **Headers**:
        - Content-Type: application/json
    - **Path Variables**:
        - {cardId}: 해당 카드 ID
    -  **Body**

  ```json
  {
      "contents": "댓글1"
  }
  ```

        - 설명:

  | # | 이름          | 타입     | 설명    | Required |
            |---|-------------|--------|-------|----------|
  | 1 | contents        | String | 댓글 내용 | O        |

</details>

<details>
<summary>2. 리스트 수정 (PATCH "cards/{cardId}/comments/{commentId}")</summary>

- 요청
    - **Headers**:
        - Content-Type: application/json
    - **Path Variables**:
        - {cardId}: 해당 카드 ID
        - {commentId}: 수정할 댓글 ID
    -  **Body**

  ```json
  {
      "contents": "댓글1"
  }
  ```

        - 설명:

  | # | 이름          | 타입     | 설명    | Required |
              |---|-------------|--------|-------|----------|
  | 1 | contents        | String | 댓글 내용 | O        |

</details>

<details>
<summary>3. 댓글 삭제 (DELETE "cards/{cardId}/comments/{commentId}")</summary>

- 요청
    - **Headers**:
        - Content-Type: application/json
    - **Path Variables**:
        - {cardId}: 해당 카드 ID
        - {commentId}: 삭제할 댓글 ID
</details>

---
## 👨‍💻 About Project

- 워크스페이스를 생성하여 그 안에서 보드와 리스트 등의 요소로 일정을 공유 / 계획 할 수 있는 프로그렘입니다.
---
## 🥵 Trouble Shooting & 🚀 Refactoring
**생성자로 Dto 받기**
- 전개: Lists 엔티티에 Card가 다대일로 연관된 상태에서 board를 조회시 Lists와 Card가 다 조회 되어야 했다
- 극복: Lists Dto를 통해 생성자에 Card를 받아서 구현 할수 있다는 것을 이번 프로젝트를 통해 깨닫게 되었다.

**Spring Security**
- 전개: jwt를 이용하여 토큰을 이용한 spring secuirty를 코드에 적용 하였다.
- 극복: 해당 토큰 생성에서, 만료 기간 설정이 너무 짧았다는 것을 알고 시간을 늘려주어 해결 할 수 있었다.

**Slack Notification**
- 전개: 슬랙에 알림이 뜨도록 연결을하는 과정을 진행하였고, 봇 토큰 생성 등록 그리고 해당 워크스페이스 주소등록을 해 주었다.
- 극복: slack 워크스페이스의 아이디를 직접적으로 넣어주고 서비스에서도 id를 호출 하는 방식으로 바꿔주어 문제를 해결 할 수 있었다.

**성능최적화**
- 전개: 카드에서 인덱싱을 통한 성능향상 과제를 하게 되었습니다. 어떤 컬럼을 인덱싱을 해야할 지 고민을 하였습니다.
- 극복: 컬럼 중 검색량이 많고, 잘 바뀌지 않는 컬럼인 title을 선정하여 인덱싱을 진행하였고, 그 결과 검색시간을 171ms에서 62ms로
  줄일 수 있었습니다.

---
## 😭 아쉬운점
- 프로젝트의 방향성을 알 수 있었지만 완벽하게 과제를 해내지 못하여 아쉬웠습니다.
- 아직 실력이 부족한 것같아 아쉬움이 있는 프로젝트였습니다.
- 배포와 S3연동 등에서 문제가 많이 발생하였고 완벽하게 해내지 못한 것이 아쉬웠습니다.

---
