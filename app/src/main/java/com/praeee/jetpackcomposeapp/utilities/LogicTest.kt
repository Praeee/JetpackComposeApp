package com.praeee.jetpackcomposeapp.utilities

fun main() {

    val palindrome = "Level"
    val list = listOf(3,6,8,1,5,10,1,7)
    println(findMiddleIndex(list))
    print(checkPalindrome(palindrome))
}

fun checkPalindrome(input : String) : String {

    val inputToList = input.lowercase().split("").filter { it.isNotEmpty() }

    val reversedList = inputToList.reversed()

    return if (inputToList == reversedList){
        "$input is a palindrome"
    } else {
        "$input isn't a palindrome"
    }

}

fun findMiddleIndex(list: List<Int>): String {
    val totalSum = list.sum()
    var leftSum = 0

    for (i in list.indices) {
        val rightSum = totalSum - leftSum - list[i]
        if (leftSum == rightSum) {
            return "middle index is $i"
        }
        leftSum += list[i]
    }

    return "index not found"
}
