
using SandwichGQL.Models.ValueObjects;
using SandwichGQL.Shared;
using Xunit;
using Assert = Xunit.Assert;

namespace SandwichGQL.Tests
{


    public class SellingPriceTest
    {

        [Fact]
        public void TestSellingPriceVerifyBelow0()
        {
           Assert.Throws<BusinessRuleValidationException>(() => new SellingPrice(-1));
        }

        [Fact]
        public void TestSellingPrice0()
        {
           Assert.Throws<BusinessRuleValidationException>(() => new SellingPrice(0));
        }

        [Fact]
        public void TestSellingPriceValid()
        {
            SellingPrice sel = new SellingPrice(22);
            Assert.Equal(sel.sellingPrice,22);
        }   
    }
}