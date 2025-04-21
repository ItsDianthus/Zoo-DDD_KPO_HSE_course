# Реализованный функционал

## a. Добавить / удалить животное

**Классы/модули:**
- `AnimalController` (методы `create` и `deleteAnimal`) — для API-интерфейса
- `AnimalService` — бизнес-логика
- `InMemoryAnimalRepository` — хранилище данных
- `Animal` — доменная модель с полной валидацией

## b. Добавить / удалить вольер

**Классы/модули:**
- `EnclosureController` (методы `create` и `delete`)
- `InMemoryEnclosureRepository` — хранилище данных
- `Enclosure` — доменная модель с методами валидации и бизнес-логикой

## c. Переместить животное между вольерами

**Классы/модули:**
- `AnimalTransferService` (метод `transfer`) — основная бизнес-логика
- `Animal` (метод `moveTo`) — доменная логика перемещения
- `AnimalMovedEvent` — доменное событие, генерируемое при перемещении
- `EventPublisher` — для оповещения о событиях

## d. Просмотреть расписание кормления

**Классы/модули:**
- `FeedingScheduleController` (методы `getAll` и `getById`)
- `FeedingSchedule` — доменная модель с расписанием
- `FeedingTime` — value object для времени кормления

## e. Добавить новое кормление в расписание

**Классы/модули:**
- `FeedingScheduleController` (метод `create`)
- `FeedingOrganizationService` — для управления процессом кормления
- `FeedingTimeEvent` — доменное событие при наступлении времени кормления

## f. Просмотреть статистику зоопарка

**Классы/модули:**
- `ZooStatisticsService` — сбор статистики по животным и вольерам  
  Использует репозитории для получения агрегированных данных

---

# Концепции Domain-Driven Design и принципы Clean Architecture

## Концепции DDD:

### Entity (Сущность)
- `Animal`, `Enclosure`, `FeedingSchedule` — имеют уникальные идентификаторы `UUID`, методы `equals`/`hashCode` на основе идентификатора

### Value Object (Объект-значение)
- `AnimalName` — инкапсулирует правила для имён животных и валидацию (неизменяемый класс)
- `FavoriteFood` — инкапсулирует данные о предпочитаемой пище (неизменяемый класс)
- `EnclosureCapacity` — правила вместимости вольеров с валидацией
- `FeedingTime` — инкапсулирует время кормления как value object

### Domain Events (Доменные события)
- `AnimalMovedEvent` — содержит информацию о перемещении животного
- `FeedingTimeEvent` — оповещает о наступлении времени кормления
- `DomainEvent` — базовый интерфейс для всех доменных событий

### Repository (Репозиторий)
- `AnimalRepository`, `EnclosureRepository`, `FeedingScheduleRepository` — интерфейсы в доменном слое
- Конкретные реализации в инфраструктурном слое (InMemory)

### Service (Сервис)
- `AnimalTransferService` — координирует операции между `Animal` и `Enclosure`
- `FeedingOrganizationService` — управляет процессом кормления
- `ZooStatisticsService` — собирает статистику с нескольких агрегатов

### Rich Domain Model
- `Animal.feed()`, `Animal.treat()`, `Animal.moveTo()` — бизнес-логика в доменной модели Animal'a
- `Enclosure.addAnimal()`, `Enclosure.removeAnimal()`, `Enclosure.clean()` — бизнес-логика в доменной модели Enclosure

## Принципы Clean Architecture:

### Независимость от фреймворков
- Доменный слой не зависит от Spring или других фреймворков

### Использование интерфейсов для связи между слоями

### Тестируемость
- Бизнес-логика изолирована в доменном и прикладном слоях

### Направление зависимостей внутрь
- `Domain` → не имеет внешних зависимостей  
- `Application` → зависит только от `Domain`  
- `Infrastructure` → зависит от `Domain` и `Application`  
- `Presentation` → зависит от `Domain` и `Application`

### Разделение на слои
- **Domain** (модели, интерфейсы, события) — бизнес-правила  
- **Application** (сервисы) — оркестрирует бизнес-логику  
- **Infrastructure** (репозитории, издатель событий) — технические детали  
- **Presentation** (контроллеры) — обработка внешних запросов

---

# Как запускать программу

## 1. Клонирование репозитория
```bash
git clone <url-репозитория>
cd KPO_DZ-2
```
## 2. Сборка проекта с maven
```bash
./mvnw clean package
```
## 3. Запуск приложения
```bash
 ./mvnw spring-boot:run
```
## Доступ к АПИ:
### Swagger UI доступен по адресу: http://localhost:8080/swagger-ui.html
### Можно использовать Postman или curl для тестирования API:
- **Получить всех животных:** GET http://localhost:8080/api/animals
- **Добавить животное:** POST http://localhost:8080/api/animals
- **Получить все вольеры:** GET http://localhost:8080/api/enclosures
- **Переместить животное:** POST http://localhost:8080/api/animals/{id}/transfer
