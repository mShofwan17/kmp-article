package me.project.kmparticle.db

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class DatabaseDriverFactory() {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(
            schema = KmpArticleDatabase.Schema,
            name = "KmpArticleDatabase.db"
        )
    }
}