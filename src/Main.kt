//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {

    var adam = Human("Adam", 44)
    adam.age++
    var adamCopy = Human("Adam", 45)
    val eve = Human("Eve", 44)
    println(adam.equals(eve)) //false
    println(adam == adamCopy) // false

    val matrix = Cyborg("Victor Stone", Int.MAX_VALUE / 2)
    val ultron = Cyborg("Ultron", 400)
    val ultronCopy = ultron.copy()
    println(matrix.equals(ultron))
    println("Comparing ultron instances ${ultron.equals(ultronCopy)}") // true
    println("You can copy a Cyborg but you can't copy a Human!")

    val firstCow = Cow("Vaquita", Size.medium)
    val secondCow = Cow("vacalola", Size.big)
    val firstDog = Dog("Cliford", Size.medium)
    playWithAnimal(firstCow)
    println()
    playWithAnimal(firstDog)
    println()
    playWithAnimal(secondCow)
    // Collections
    val myList = mutableListOf("Anderson", "Juan", "Pedro", "Pedro", "Alguien")
    val mySet = mutableSetOf("Hola", "Hello", "Konichigua")
    val myDictionary = mutableMapOf(28113110 to "Anderson",28110345 to "Luis", 9762309 to "Elba" )
    playWithCollections(myList)
    println()
    println("Set Playing")
    playWithCollections(mySet)

    println("Map playing")
    for (value in myDictionary){
        println(value)
    }
    myDictionary.map {
        println("${it.key} belongs to ${it.value}")
    }

    // Returning string in a List with a length minor to 5
    val filterList = myList.filter{
       it.length < 5
    }
    println(filterList )

    //Returning the longest String in a List
    val longestNameInList = myList.maxBy { it.length }
    println(longestNameInList)
}

class Dog : Animal
{
    override val name: String
    override val size: Size

    constructor(name: String, size: Size) {
        this.name = name
        this.size = size
    }

    override fun walk() {
        println("$name is walking")

    }
}

class Cow(override val name: String, override var size:Size) : Animal {
    override fun walk() {
        println("$name the Cow is walking")
    }
    fun eat(){
        println("$name is eating")
        this.size = Size.big;
        println("$name is bigger now!")

    }
}

enum class Size{
    small,
    medium,
    big
}

interface Animal {
    val name: String
    val size: Size
        get() = Size.medium // when not size provided
    fun walk();
}


class Human(val name: String, var age: Int);

// Cyborg is a data class
data class Cyborg (val name: String, var age: Int){
    fun talk(){
        println("Hello, Human I am $name and I am a Cyborg!")
    }
}


/*
* @description: Use an Animal that implement the Animal interface
* depending of the kind of animal the function call different method of the class
* */
fun playWithAnimal(animal: Animal){
    animal.walk()
    // Cannot call this method in this scope because it is not a "Cow" and it just only access
    // the properties in the contract (interface)
    //animal.eat()
    when (animal){
        is Cow -> {
            if (animal.size == Size.big || animal.name == "lavacalola"){
                println("Animal is as big as it could be. Its not going to eat now!")
            }else{
                animal.eat()
            }
        }
        else -> println("It is not a cow!")
    }
}

/*
* @Description: This function recibe a collection as the parameter and depending of the type of collection play with it
*
* */
fun playWithCollections  (collection: MutableCollection<String>){
   when (collection){
       is MutableList<String> -> {
          collection.add("Value")
          collection.add("Value2")
          collection.add("Value3")
          collection.add("Value3")
          collection.add("Value3")
          println("Collection is a list of size ${collection.size}")

          collection.forEach {
              println(it)
              //repeated values allowed
          };
          collection.removeAt(0)
      }
       is MutableSet<String> -> {
           collection.add("Value")
           collection.add("Value2")
           collection.add("Value3")
           collection.add("Value3")
           collection.add("Value3")

           collection.forEach({
               println(it)
               //repeated not allowed
           })
       }
   }

}