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
API Endpoints
GitHub API

GET /api/v1/GithubAPI/get-issues/{owner}/{repo}/{projectNumber}
Получение задач из GitHub. Возвращает Mono с задачами.

GET /api/v1/GithubAPI/get-collaborators/{owner}/{repo}
Получение пользователей из GitHub. Возвращает Mono с пользователями.

Участники (Person)

GET /api/v1/Person/get
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

POST /api/v1/Person/create
Создание участника команды в базе.

Input:
json

    {
        "name": "<NAME>",
        "skillSet": ["<SKILL>", "<SKILL>", ...]
    }

Output: (аналогично input)

Проекты (Project)

GET /api/v1/Project/getAll
Получение всех проектов.

Output:
json

    [
        {
            "id": <ID>,
            "title": "PROJECT_TITLE"
        },
        ...
    ]

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
        "taskTitle": "<TASK_TITLE>",
        "projectTitle": "PROJECT_TITLE"
    }

Output: (аналогично input)

POST /api/v1/Project/members
Создание связи между проектом и участником.

Input:
json

    {
        "projectTitle": "PROJECT_TITLE",
        "name": "<COLLABORATOR_NAME>"
    }

    Output: (аналогично input)

Задачи (Task)

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
        "name": "<COLLABORATOR_NAME>",
        "title": "<TASK_TITLE>"
    }

Output: (аналогично input)

AI Endpoints

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