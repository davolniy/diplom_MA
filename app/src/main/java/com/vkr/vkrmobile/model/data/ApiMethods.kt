package com.vkr.vkrmobile.model.data

object ApiMethods {
    object Launch {
        const val AppInit = "AppConfigurations/AppInit"
    }
    object Users {
        const val Authorization = "Users/Authorization"
        const val Registration = "Users/Registration"
    }
    object News {
        const val GetAllNews = "News/AllNews"
        const val GetAllActions = "News/AllActions"
        const val GetCompanyActions = "News/CompanyActions"
        const val GetNews = "News/News"
    }
    object Catalogs {
        const val GetCompanyCatalogs = "Catalogs/CompanyCatalogs"
        const val GetCatalogWithProducts = "Catalogs/CatalogWithProducts"
    }
    object Carts {
        const val GetAllCarts = "Carts/AllCarts"
        const val AddToCart = "Carts/AddToCart"
        const val UpdateProductCount = "Carts/UpdateProductCount"
    }
    object Chats {
        const val GetChats = "Chats/Chats"
        const val GetChatMessages = "Chats/ChatMessages"
        const val SendMessage = "Chats/SendMessage"
    }
    object Orders {
        const val GetOrders = "Orders/Orders"
        const val MakeOrder = "Orders/MakeOrder"
    }
    object Products {
        const val GetProduct = "Products/Product"
    }
    object Companies {
        const val GetParentCompanies = "Companies/ParentCompanies"
        const val GetBranchCompanies = "Companies/BranchCompanies"
        const val GetCompaniesWithBranches = "Companies/CompaniesWithBranches"
        const val GetCompany = "Companies/Company"
    }
    object Requests {
        const val GetRequests = "Requests/Requests"
        const val MakeRequest = "Requests/MakeRequest"
        const val GetCompaniesRequestTypes = "Requests/CompaniesRequestTypes"
        const val GetRequestType = "Requests/RequestType"
    }
    object Reviews {
        const val GetReviews = "Reviews/Reviews"
        const val MakeReview = "Reviews/MakeRequest"
        const val GetCompanyReviews = "Reviews/RequestTypes"
    }
    object Services {
        const val GetServices = "Services/Reviews"
        const val GetEmployees = "Services/Employees"
        const val MakeService = "Services/MakeService"
    }
}