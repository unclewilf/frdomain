package cafe

import scalaz._

sealed trait Sandwich {
    def name: String
    def cost: BigDecimal
}

case class HamSandwich (name: String, cost: BigDecimal, slicesOfHam: Int) extends Sandwich

object Sandwich {

    def makeHamSandwich(name: String, cost: BigDecimal, slicesOfHam: Int): ValidationNel[String, HamSandwich] = {

        def validateName(name: String): ValidationNel[String, String] = name match {
            case null => Failure(NonEmptyList("Name is missing"))
            case s => Success(s)
        }

        def validateCost(cost: BigDecimal): ValidationNel[String, BigDecimal] = cost match {
            case c if c < 1 => Failure(NonEmptyList("Cost is negative"))
            case c if c > 10 => Failure(NonEmptyList("Cost is greater than 10"))
            case c => Success(c)
        }

        def validateHam(slicesOfHam: Int): ValidationNel[String, Int] = slicesOfHam match {
            case c if c < 1 => Failure(NonEmptyList("Not enough ham"))
            case c if c > 3 => Failure(NonEmptyList("Too much ham"))
            case c => Success(c)
        }

        for {
            n <- validateName(name)
            c <- validateCost(cost)
            h <- validateHam(slicesOfHam)
        } yield
            HamSandwich(name, cost, slicesOfHam)
    }
}
