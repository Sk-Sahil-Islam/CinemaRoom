import java.lang.Exception

fun totalProfit(row: Int, seat: Int):Int {
    if (row*seat <= 60)
        return row * seat * 10
    else {
        if(row%2==0)
            return ((row/2) * seat * 10) + ((row/2) * seat * 8)
        else return ((row/2) * seat * 10) + ((row/2+1) * seat * 8)
    }
}

fun ticketPrice(totalRow: Int, totalSeat: Int, row: Int): Int {
    return if(totalRow * totalSeat <= 60)
        10
    else{
        if(row <= totalRow/2) 10
        else 8
    }
}

fun cinemaCreate(r: Int, c: Int): MutableList<MutableList<String>> {
    return MutableList(r) { MutableList(c) { "S" } }
}

fun printCinema(cin: MutableList<MutableList<String>>) {
    val seat = cin[0].size
    val row = cin.size
    print("\nCinema:\n  ")
    (1..seat).forEach {it -> print("$it ") }
    println()
    (1..row).forEach {it -> println("$it ${cin[it-1].joinToString(" ")}") }
}

var ticketsSold = 0
var currentIncome = 0

fun main(){

    println("Enter the number of rows:")
    val row = readln().toInt()
    println("Enter the number of seats in each row:")
    val seat = readln().toInt()
    val cinema = cinemaCreate(row, seat)

    var exit = false
    do {
        println("""
            
            1. Show the seats
            2. Buy a ticket
            3. Statistics
            0. Exit
        """.trimIndent())

        when(readln().toInt()){
            1 -> printCinema(cinema)
            2 -> purchaseTickets(cinema)
            3 -> statistics(cinema)
            0 -> exit = true
            else -> exit = true
        }
    }while(exit == false)
}
fun purchaseTickets(cinema: MutableList<MutableList<String>>) {
    val row = cinema.size
    val seat = cinema[0].size
    println("\nEnter a row number:")
    val rowBook = readln().toInt()
    println("Enter a seat number in that row:")
    val seatBook = readln().toInt()
    try {
        if(cinema[rowBook-1][seatBook-1] == "B") {
            println("\nThat ticket has already been purchased!")
            return purchaseTickets(cinema)
        }
        cinema[rowBook-1][seatBook-1] = "B"
        ++ticketsSold
        currentIncome += ticketPrice(row, seat, rowBook)
        println("\nTicket price: $${ticketPrice(row, seat, rowBook)}")
    } catch (e: Exception) {
        println("\nWrong input!")
        return purchaseTickets(cinema)
    }
}
fun statistics(cinema: MutableList<MutableList<String>>){
    val row = cinema.size
    val seat = cinema[0].size
    val percentage = ticketsSold * 100f / (row * seat).toFloat()
    val formatPercentage = "%.2f".format(percentage)

    println("Number of purchased tickets: $ticketsSold")
    println("Percentage: $formatPercentage%")
    println("Current income: $$currentIncome")
    println("Total income: $${totalProfit(row, seat)}")
}