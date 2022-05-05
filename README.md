What architecture-layers did I create and why?

I follow Android architecture guideline that's why I used MVVM and some jetpack components
because it is easy to apply separation of concern and no need to have view reference like MVP that helps for memory usage and configuration changes and so on

I prefer divide the layers below to apply separation of concern and single responsibility principle to create a clean MVVM architecture like readable, testable and maintainable
* Data -> RepositoryImpl, Repository Interface
* Presentation -> Views and ViewModel, UIState -> Success, Error, Loading

I used the tech stack and libraries;
* Fully Kotlin
* MVVM Architecture
* Retrofit for network
* Koin for DI
* Coroutines for async operations
* MockK for Unit Tests
* Jetpack Compose for UI
* Basic Error Handling
* Navigation architecture component to go to Detail Page

What would I do if I had more time?
* GraphQL implementation
* Pagination implementation
* Better UI implementation
* Better Error handling
* More detailed Unit tests for each single unit
* More detailed UI tests for each single action
* Domain layer implementation
* Divide models depends on layers like ApiModel, DomainModel and UiModel
* Mapper for models for ApiModel, DomainModel and UiModel