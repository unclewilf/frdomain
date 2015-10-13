package cafe

import org.scalacheck.Gen
import org.scalatest.prop.PropertyChecks
import org.scalatest.{Matchers, PropSpec}

object SandwichGenerator {

    val validNames = for (n <- Gen.alphaStr) yield n
    val validCost = for (c <- Gen.choose(1, 10)) yield BigDecimal(c)
    val validSlices = for (s <- Gen.choose(1, 3)) yield s

    val invalidSlices = Gen.oneOf(-1, 0, 4)
}

class SandwichSpec extends PropSpec with PropertyChecks with Matchers {

    import SandwichGenerator._

    property("A ham sandwich should pass validation") {

        forAll((validNames, "name"), (validCost, "cost"), (validSlices, "slices")) {
            (name, cost, slicesOfHam) =>

                val sandwich = Sandwich
                    .makeHamSandwich(name, cost, slicesOfHam)
                    .getOrElse(throw new IllegalArgumentException)

                sandwich.name shouldBe name
                sandwich.cost shouldBe cost
                sandwich.slicesOfHam shouldBe slicesOfHam
        }
    }

    property("A ham sandwich should fail if invalid slices provided") {

        forAll((validNames, "name"), (validCost, "cost"), (invalidSlices, "slices")) {
            (name, cost, slicesOfHam) =>

                val sandwich = Sandwich.makeHamSandwich(name, cost, slicesOfHam)
                sandwich.isFailure shouldBe true
        }
    }
}
