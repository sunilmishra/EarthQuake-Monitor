package com.codewithmisu.earthquakedemo.core

/**
 * Represents the state of a UI that loads data asynchronously.
 *
 * This sealed interface follows the LCE (Loading-Content-Error) pattern, providing a
 * type-safe way to represent the different states of a data-loading operation.
 *
 * @param T The type of the data to be loaded. It's covariant (`out`) to allow for
 *   flexibility in handling subtypes.
 */
sealed interface UiState<out T> {

    /**
     * Represents the initial, idle state before any data loading has been initiated.
     *
     * This is typically the default state of a UI component before it triggers a data fetch.
     */
    data object Idle : UiState<Nothing>

    /**
     * Represents the loading state, where an asynchronous operation is in progress.
     *
     * The UI should typically display a loading indicator, such as a progress bar,
     * during this state.
     */
    data object Loading : UiState<Nothing>

    /**
     * Represents the success state, where the content has been successfully fetched.
     *
     * @param T The type of the loaded content.
     * @property content The successfully loaded content.
     */
    data class Success<T>(val content: T) : UiState<T>

    /**
     * Represents the failure state, where an error occurred during the data loading operation.
     *
     * @property message A descriptive message about the error, suitable for display to the user.
     * @property throwable The optional [Throwable] that caused the error, useful for debugging
     * and logging purposes.
     */
    data class Failure(val message: String, val throwable: Throwable? = null) : UiState<Nothing>
}
