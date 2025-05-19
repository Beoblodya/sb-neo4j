Сервис работы с базой и получения данных с git

По папкам:

controller - контроллеры

dto - дтошки. pojo для моделей

model - модели нодов в бд

QueryResults - результаты запросов в бд. pojo

repository - репозитории

request - запросы. pojo

service - сервисы

util - утилиты(парсер)

По названиям:

Github - все относящиеся к получению данных с гита

Project - работа с проектами в базе

Task - работа с тасками в базе

Person - работа с участниками в базе

BUILD:
docker compose up -d --build

Нейронка должна быть на эндпоинте http://127.0.0.1:5000/api/v1

ENDPOINTS
http://127.0.0.1:8080/api/v1/GithubAPI/get-issues/{owner}/{repo}/{projectNumber} - Получение тасков из гита. Возвращает Mono с тасками\
http://127.0.0.1:8080/api/v1/GithubAPI/get-collaborators/{owner}/{repo} - Получение юзеров из гита. Возвращает Mono с юзерами\

GET http://127.0.0.1:8080/api/v1/Person/get - Получение всех участников команды из базы
INPUT: - 
OUTPUT: 
[
    {"name": "<NAME>", 
    "skillSet": [
            "<SKILL>", 
            "<SKILL>", 
            ...
        ]
    },
    ...
]
POST http://127.0.0.1:8080/api/v1/Person/create - Создание участника команды в базе. На вход json с именем. На выход json с именем\
INPUT:
{
    "name": "<NAME>",
    "skillSet": [
        "<SKILL>",
        "<SKILL>",
        ...
        ]
}
OUTPUT:
{
    "name": "<NAME>",
    "skillSet": [
            "<SKILL>",
            "<SKILL>",
            ...
        ]
}
GET http://127.0.0.1:8080/api/v1/Project/getAll - Получение всех проектов\
INPUT: -
OUTPUT:
[
    {
        "id": <ID>,
        "title": "PROJECT_TITLE"
    },
    ...
]
POST http://127.0.0.1:8080/api/v1/Project/create - Создание проектов команды в базе.На вход json с названием. На выход json с названием\
INPUT:
{
    "title": "<TITLE>",
}
OUTPUT:
{
    "title": "<TITLE>",
}
POST http://127.0.0.1:8080/api/v1/Project/contains - Создание связи между проектом и таском. На вход json с названием проекта и названием таска. На выход json с названием проекта и названием таска\
INPUT:
{
    "taskTitle": "<TASK_TITLE>",
    "projectTitle": "PROJECT_TITLE"
}
OUTPUT:
{
    "taskTitle": "<TASK_TITLE>",
    "projectTitle": "PROJECT_TITLE"
}
POST http://127.0.0.1:8080/api/v1/Project/members - Создание связи между проектом и участником команды. На вход json с названием проекта и именем. На выход json с названием проекта и именем\
INPUT:
{
    "projectTitle":"PROJECT_TITLE",
    "name": "<COLLABORATOR_NAME>"
}
OUTPUT:
{
    "projectTitle":"PROJECT_TITLE",
    "name": "<COLLABORATOR_NAME>"
}
GET http://127.0.0.1:8080/api/v1/Task/getAll - Получение всех тасков\
INPUT: -
OUTPUT:
[
    {
        "id": <ID>,
        "title": "<TITLE>",
        "content": "<CONTENT>",
        "status": "<OPEN/CLOSED>"
    },
    ...
]
POST http://127.0.0.1:8080/api/v1/Task/create - Создание таска в базе. На вход json с названием. На выход json с названием\
INPUT:
{
    "title": "<TITLE>",
    "content": "<CONTENT>",
    "status": "<OPEN/CLOSED>"
}
OUTPUT:
INPUT:
{
    "title": "<TITLE>",
    "content": "<CONTENT>",
    "status": "<OPEN/CLOSED>"
}
http://127.0.0.1:8080/api/v1/Task/assign - Создание связи между таском и участником. На вход json с именем участника и названием таска. На выход json с именем участник и названием таска
INPUT:
{
    "name": "<COLLABORATOR_NAME>",
    "title": "<TASK_TITLE>"
}
OUTPUT:
{
    "name": "<COLLABORATOR_NAME>",
    "title": "<TASK_TITLE>"
}

GET http://127.0.0.1:8080/api/v1/AI/relations - распределение всех тасков между всеми исполнителями от нейронки
INPUT: -
OUTPUT:
{
"assignments": [
        {
            "name": "COLLABORATOR_NAME",
            "title": "TASK_TITLE"
        }
    ]
}