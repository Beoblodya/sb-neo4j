# Сервис работы с базой и получения данных с Git

## Структура проекта

controller/ - контроллеры
dto/ - DTO (POJO для моделей)
model/ - модели нодов в БД
QueryResults/ - результаты запросов в БД (POJO)
repository/ - репозитории
request/ - запросы (POJO)
service/ - сервисы
util/ - утилиты (парсер)


## Модули

- **Github** - всё относящееся к получению данных с Git
- **Project** - работа с проектами в базе
- **Task** - работа с задачами в базе
- **Person** - работа с участниками в базе

## Сборка и запуск

```bash
docker compose up -d --build

Примечание: Нейросетевая служба должна быть доступна по адресу http://127.0.0.1:5000/api/v1
```

## API endpoints

-**GitHub API**
```bash
GET /api/v1/GithubAPI/get-issues/{owner}/{repo}/{projectNumber}
Получение задач из GitHub. Возвращает Mono с задачами.

GET /api/v1/GithubAPI/get-collaborators/{owner}/{repo}
Получение пользователей из GitHub. Возвращает Mono с пользователями.
```

-**Участники (Person)**
```bash
GET /api/v1/Person/getAll
Получение всех участников команды из базы.

Output:
json
    [
        {
            "name": "<NAME>",
            "skillSet": ["<SKILL>", "<SKILL>", ...]
        },
        ...
    ]

GET /api/v1/Person/get-person/{id}
Получение участника команды по id

Output:
json

    {
        "name": "<NAME>",
        "skillSet": ["<SKILL>", "<SKILL>", ...]
    }

GET /api/v1/Person/get-person-by-name/{name}

Output:
json

    {
        "name": "<NAME>",
        "skillSet": ["<SKILL>", "<SKILL>", ...]
    }

POST /api/v1/Person/create
Создание участника команды в базе.

Input:
json

    {
        "name": "<NAME>",
        "skillSet": ["<SKILL>", "<SKILL>", ...]
    }

Output: (аналогично input)

GET /api/v1/Person/projects-of-person/{id}
Подучение проектов по id участника команды

Output:
json

    [
        {
            "id": <ID>,
            "title": "<PROJECT_TITLE>"
        },
        ...
    ]
    
GET /api/v1/Person/tasks-of-person/{id}
Получение тасков по id участника команды

Input:
json

    {
        "personId": "<PERSON_ID>"
    }

Output:
    [
        {
            "id": <ID>,
            "title": "<TITLE>",
            "content": "<CONTENT>",
            "status": "<OPEN/CLOSED>"
        },
        ...
    ]
    
POST /api/v1/Person/addSkillsById
Добавление скиллов

Input:
json

    {
        "personId": "<PERSON_ID>",
        "personSkillSet": ["<SKILL>", "<SKILL>", ...]
    }
    
Output:
json

    {
        "name": "<NAME>",
        "skillSet": ["<SKILL>", "<SKILL>", ...]
    }
    
POST /api/v1/Person/deleteASkillById
Удаление скилла

Input:
json

    {
        "personId": "<PERSON_ID>",
        "skill": "<SKILL>"
    }
    
Output:
json

    {
        "name": "<NAME>",
        "skillSet": ["<SKILL>", "<SKILL>", ...]
    }
    
POST /api/v1/Person/updateSkillSetById
Обновление скиллов(замена массива)

Input:
json

    {
        "personId": "<PERSON_ID>",
        "personSkillSet": ["<SKILL>", "<SKILL>", ...]
    }
    
Output:
json

    {
        "name": "<NAME>",
        "skillSet": ["<SKILL>", "<SKILL>", ...]
    }

```

-**Проекты (Project)**
```bash
GET /api/v1/Project/getAll
Получение всех проектов.

Output:
json

    [
        {
            "id": <ID>,
            "title": "<PROJECT_TITLE>"
        },
        ...
    ]

GET /api/v1/Project/get-project{id}
Получение проекта по id

Output:
json
    {
        "id": <ID>,
        "title": "<PROJECT_TITLE>"
    }

POST /api/v1/Project/create
Создание проекта.

Input:
json

    {
        "title": "<TITLE>"
    }

Output: (аналогично input)

POST /api/v1/Project/contains
Создание связи между проектом и задачей.

Input:
json

    {
        "projectId": "<PROJECT_ID>",
        "taskId": "<TASK_ID>"
    }

Output: json

    {
        "projectId": "<PROJECT_ID>",
        "projectTitle": "<PROJECT_TITLE>",
        "taskId": "<TASK_ID>",
        "taskTitle": "<TASK_TITLE>"
    }

POST /api/v1/Project/members
Создание связи между проектом и участником.

Input:
json

    {
        "projectId": "<PROJECT_ID>",
        "personId": "<PERSON_ID>"
    }

Output:
json

    {
        "projectId": "<PROJECT_ID>",
        "projectTitle": "<PROJECT_TITLE>",
        "personId": "<PERSON_ID>",
        "personName": "<PERSON_NAME>"
    }
    
GET /api/v1/Project/tasks-of-project/{id}
Получение всех тасков проекта

Output:
json

    [
        {
            "id": <ID>,
            "title": "<TITLE>",
            "content": "<CONTENT>",
            "status": "<OPEN/CLOSED>"
        },
        ...
    ]
    
GET /api/v1/Project/members-of-project/{id}
получение всех участников проекта
    
Output:
json

    {
        "name": "<NAME>",
        "skillSet": ["<SKILL>", "<SKILL>", ...]
    }
```

-**Задачи (Task)**
```bash
GET /api/v1/Task/getAll
Получение всех задач.

Output:
json

    [
        {
            "id": <ID>,
            "title": "<TITLE>",
            "content": "<CONTENT>",
            "status": "<OPEN/CLOSED>"
        },
        ...
    ]
    
GET /api/v1/Task/get-task/{id}
Получение таска по id
  
Output:
    {
        "id": <ID>,
        "title": "<TITLE>",
        "content": "<CONTENT>",
        "status": "<OPEN/CLOSED>"
    }

POST /api/v1/Task/create
Создание задачи.

Input:
json

    {
        "title": "<TITLE>",
        "content": "<CONTENT>",
        "status": "<OPEN/CLOSED>"
    }

Output: (аналогично input)

POST /api/v1/Task/assign
Создание связи между задачей и участником.

Input:
json

    {
        "personId": "<PERSON_ID>",
        "taskId": "<TASK_ID>"
    }

Output:
json

    {
        "personId": "<PERSON_ID>",
        "personName": "<PERSON_NAME>"
        "taskId": "<TASK_ID>",
        "taskTitle": "<TASK_TITLE>"
    }
    
GET /api/v1/Task/get-responsible-for-task/{id}
Получение участников по id таска
   
Output:
json

    {
        "name": "<NAME>",
        "skillSet": ["<SKILL>", "<SKILL>", ...]
    }

```

-**AI Endpoints**
```bash
GET /api/v1/AI/relations
Распределение всех задач между исполнителями (от нейросети).

Output:
json

    {
        "assignments": [
            {
                "name": "COLLABORATOR_NAME",
                "title": "TASK_TITLE"
            }
        ]
    }
```