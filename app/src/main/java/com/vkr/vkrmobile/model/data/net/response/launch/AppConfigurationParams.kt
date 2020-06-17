package com.vkr.vkrmobile.model.data.net.response.launch

import com.google.gson.annotations.SerializedName
import com.vkr.vkrmobile.model.data.ParamsKeys

class AppConfigurationParams (
    @SerializedName(ParamsKeys.AUTH_REQUIRED_PARAM_KEY) val authRequired: Boolean = false,
    @SerializedName(ParamsKeys.MENU_VIEW_MODE_PARAM_KEY) val menuViewMode: String = "Top",
    @SerializedName(ParamsKeys.NEWS_VIEW_MODE_PARAM_KEY) val newsViewMode: String = "Cards",
    @SerializedName(ParamsKeys.COMPANIES_VIEW_MODE_PARAM_KEY) val companiesViewMode: String = "List",
    @SerializedName(ParamsKeys.COMPANY_MENU_VIEW_MODE_PARAM_KEY) val companyMenuViewMode: String = "Slider",
    @SerializedName(ParamsKeys.COMPANY_LOGO_VIEW_MODE_PARAM_KEY) val companyLogoViewMode: Boolean = true,
    @SerializedName(ParamsKeys.ACCENT_COLOR_PARAM_KEY) val accentColor: String = "#F44336",
    @SerializedName(ParamsKeys.POSITIVE_COLOR_PARAM_KEY) val positiveColor: String = "#41CA35",
    @SerializedName(ParamsKeys.NEGATIVE_COLOR_PARAM_KEY) val negativeColor: String = "#FF5A5A",
    @SerializedName(ParamsKeys.CONFIGURATION_FUNCTIONS_PARAM_KEY) val configurationFunctions: ConfigurationFunctions = ConfigurationFunctions()
)
