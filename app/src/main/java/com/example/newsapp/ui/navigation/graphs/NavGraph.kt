package com.coding.meet.newsapp.ui.navigation.graphs

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.coding.meet.newsapp.data.model.Article
import com.coding.meet.newsapp.ui.article_detail.ArticleDetailScreen
import com.coding.meet.newsapp.ui.bookmark.BookmarkScreen
import com.coding.meet.newsapp.ui.headline.HeadlineScreen
import com.coding.meet.newsapp.ui.navigation.Route
import com.coding.meet.newsapp.ui.search.SearchScreen
import com.coding.meet.newsapp.ui.setting.SettingScreen
import com.coding.meet.newsapp.ui.setting.SettingViewModel
import kotlinx.serialization.json.Json

/**
 * Created 28-02-2024 at 03:04 pm
 */
@Composable
fun NavGraph(
    rootNavController: NavHostController,
    innerPadding: PaddingValues,
    settingViewModel: SettingViewModel
) {
    NavHost(
        navController = rootNavController,
        startDestination = Route.Headline,
    ) {
        composable<Route.Headline> {
            HeadlineScreen(rootNavController = rootNavController, paddingValues = innerPadding)
        }
        composable<Route.Search> {
            SearchScreen(rootNavController = rootNavController, paddingValues = innerPadding)
        }
        composable<Route.Bookmark> {
            BookmarkScreen(rootNavController = rootNavController, paddingValues = innerPadding)
        }
        composable<Route.NewsDetail> {
            rootNavController.previousBackStackEntry?.savedStateHandle?.get<String>("article")?.let { article ->
                val currentArticle: Article = Json.decodeFromString(article)
                ArticleDetailScreen(rootNavController, currentArticle)
            }
        }
        composable<Route.SettingDetail> {
            SettingScreen(navController = rootNavController, settingViewModel)
        }
    }
}