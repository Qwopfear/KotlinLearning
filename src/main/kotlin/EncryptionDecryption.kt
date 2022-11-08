import java.util.Arrays
import java.util.Scanner

fun main(args : Array<String>) {
    val stage1 = mySol("we found a treasure!")
    val stage2 = knowledgeIsKey()

    println("$stage1\n$stage2")

    // stage 3
    cipher()

    // stage 4

    cipherWithArgs(args)

    //stage 5 / 6
    
    cipherWithArgsAndFileReading(args)
    
    
    
}


fun cipherWithArgsAndFileReading(args: Array<String>) {
    var data = ""
    var mode = "enc"
    var key = 0
    var inputPath = ""
    var outputPath = ""
    var result = ""
    var shift = "shift"
    for (index in args.indices) {
        when (args[index]) {
            "-data" -> data = args[index + 1]
            "-mode" -> mode = args[index + 1]
            "-key" -> key = args[index + 1].toInt() % 26
            "-in" -> inputPath = args[index + 1]
            "-out" -> outputPath = args[index + 1]
            "-alg" -> shift = args[index + 1]
        }
    }

    if (data.isEmpty()) {
        val inputFile = File(inputPath)
        try {
            data = inputFile.readText()
        } catch (e: FileNotFoundException) {
            mode = "Error"
        }
    }

    if (mode != "Error") {
        when (mode) {
            "enc" -> result = encryption(data, key, shift)
            "dec" -> result = decryption(data, key, shift)
        }

        if (outputPath.isEmpty()) {
            println(result)
        } else {
            val outputFile = File(outputPath).also { it.writeText(result) }
        }
    } else {
        println(mode)
    }

}



fun cipherWithArgs(args: Array<String>) {
    var mode : String = "enc"
    var data : String = ""
    var key : Int = 0
    for (index in args.indices){
        when (args[index]){
            "-mode" -> mode = args[index + 1]
            "-key" ->  key =  args[index + 1].toInt()
            "-data" -> data = args[index + 1]
        }
    }

//    println("$mode $key $data")
    when (mode) {
        "enc" ->  println(encryption(data, key))
        "dec" ->  println(decryption(data, key))
        else  ->  println("There is no available action")
    }
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

fun decryption(text: String, key: Int, shift: String = "shift"): String {
    var result = ""
    when (shift) {
        "unicode" -> text.forEach { result += it - key }.toString()
        "shift" -> text.forEach {
            result += if (it.code in 97..122 || it.code in 65..90) {
                if (it.code - key < 97) {
                    (123 - (abs(97 - (it.code - key)))).toChar()
                } else if (it.code - key < 65 && it.code < 97) {
                    (91 - (abs(65 - (it.code - key)))).toChar()
                } else {
                    (it.code - key).toChar()
                }
            } else {
                it
            }
        }

    }

    return result
}

fun encryption(text: String, key: Int, shift: String = "shift"): String {
    var result = ""
    when (shift) {
        "unicode" -> text.forEach { result += it + key }
        "shift" -> text.forEach {
            result += if (it.code in 97..122 || it.code in 65..90) {
                if (it.code + key > 122) {
                    (96 + (it.code + key - 122)).toChar()
                } else if (it.code + key > 90 && it.code < 97) {
                    (64 + (it.code + key - 90)).toChar()
                } else {
                    (it.code + key).toChar()
                }
            } else {
                it
            }
        }
    }

    return result
}
