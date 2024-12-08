package com.example.androidsnapshottest

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onRoot
import com.example.androidsnapshottest.component.ProfileCard
import com.karumi.shot.ScreenshotTest
import org.junit.Rule
import org.junit.Test

class ProfileCardSnapshotTest : ScreenshotTest {

    @get:Rule
    val composeRule = createComposeRule()

    //config the ProfileCard Component version between 1 and 2
    private val VERSION = 2

    @Test
    fun testProfileCardWithDefaultData() {
        val mockProfileModel = ProfileModel(
            name = "John", country = "Thailand", profileImageUrl = null
        )
        renderComponent(mockProfileModel = mockProfileModel)
        compareScreenshot(composeRule.onRoot())
    }

    @Test
    fun testProfileCardWithLongName() {
        val mockProfileModel = ProfileModel(
            name = "John Jacob Jingleheimer Schmidt Alexander Maximilian Bartholomew Montgomery Fitzgerald Wellington Theodore Augustus Benjamin Franklin Washington Jefferson Lincoln Roosevelt Churchill Napoleon Bonaparte Caesar Augustus Constantine Charlemagne Attila the Hun Genghis Khan Marco Polo Leonardo da Vinci Michelangelo Buonarroti",
            country = "Thailand",
            profileImageUrl = null
        )
        renderComponent(mockProfileModel = mockProfileModel)
        compareScreenshot(composeRule.onRoot())
    }

    @Test
    fun testProfileCardWithEmptyCountry() {
        val mockProfileModel = ProfileModel(
            name = "John", country = "", profileImageUrl = null
        )
        renderComponent(mockProfileModel = mockProfileModel)
        compareScreenshot(composeRule.onRoot())
    }

    @Test
    fun testProfileCardWithLongCountry() {
        val mockProfileModel = ProfileModel(
            name = "John",
            country = "The United Kingdom of Great Britain and Northern Ireland",
            profileImageUrl = null
        )
        renderComponent(mockProfileModel = mockProfileModel)
        compareScreenshot(composeRule.onRoot())
    }

    @Test
    fun testProfileCardDisplaysWithLongNameAndCountry() {
        val mockProfileModel = ProfileModel(
            name = "John Jacob Jingleheimer Schmidt Alexander Maximilian Bartholomew Montgomery Fitzgerald Wellington Theodore Augustus Benjamin Franklin Washington Jefferson Lincoln Roosevelt Churchill Napoleon Bonaparte Caesar Augustus Constantine Charlemagne Attila the Hun Genghis Khan Marco Polo Leonardo da Vinci Michelangelo Buonarroti",
            country = "The United Kingdom of Great Britain and Northern Ireland",
            profileImageUrl = null
        )
        renderComponent(mockProfileModel = mockProfileModel)
        compareScreenshot(composeRule.onRoot())
    }

    private fun renderComponent(mockProfileModel: ProfileModel) {
        composeRule.setContent {
            when (VERSION) {
                1 -> {
                    ProfileCard(
                        name = mockProfileModel.name,
                        country = mockProfileModel.country,
                        profileImageUrl = mockProfileModel.profileImageUrl,
                    )
                }

                2 -> {
                    ProfileCard(name = mockProfileModel.name,
                        country = mockProfileModel.country,
                        profileImageUrl = mockProfileModel.profileImageUrl,
                        icon = {
                            Icon(
                                imageVector = Icons.Filled.MoreVert,
                                contentDescription = "More Options"
                            )
                        },
                        onProfileClick = {
                        },
                        onMoreVertClick = {
                        })
                }
            }
        }
    }

}