import java.util.Arrays
import java.util.Scanner

fun main() {
    val stage1 = mySol("we found a treasure!")
    val stage2 = knowledgeIsKey()

    println("$stage1\n$stage2")

    // stage 3
    cipher()


}


fun mySol(text: String): String {
    val array = text.lowercase().toCharArray()
    var result = ""


    for (i in array.indices) {
        if (array[i].code in 97..122) {
            if (array[i].code in 97..110) {
                array[i] = (122 - (array[i].code - 97)).toChar()
            } else {
                array[i] = (97 + (122 - array[i].code)).toChar()
            }
        }
        result += array[i]
    }



    return result
}

fun knowledgeIsKey(): String {
    val text = readln()
    val shift = readln().toInt()
    var result = ""
    for (i in text.indices) {
        if (text[i].code >=  'a'.code && text[i].code <= 'z'.code) {
            if (text[i].code + shift > 'z'.code) {
                result += ('a'.code - 1 + (text[i].code + shift - 'z'.code)).toChar()
            } else {
                result += text[i] + shift
            }
        } else {
            result += text[i]
        }

    }

    return result

}

fun cipher(){
    val action = readln()
    val text = readln()
    val shift = readln().toInt()


    when (action) {
        "enc" ->  println(encryption(text, shift))
        "dec" ->  println(decryption(text, shift))
        else  ->  println("There is no available action")
    }


}

fun decryption(text: String, shift: Int) : String {
    var result = ""
    for (i in text.indices) {
        result += text[i] - shift
    }
    return result
}

fun encryption(text: String, shift: Int) : String{
    var result = ""
    for (i in text.indices) {
        result += text[i] + shift
    }
    return result
}
