using SandwichGQL.Models.ValueObjects;
using SandwichGQL.Shared;
using Xunit;
using Assert = Xunit.Assert;

namespace SandwichGQL.Tests
{


    public class DesignationTest
    {

        [Fact]
        public void TestDesignationError()
        {
           Assert.Throws<BusinessRuleValidationException>(() => new Designation(""));
        }


        [Fact]
        public void TestDesignationValid()
        {
            Designation sel = new Designation("aaaa");
            Assert.Equal(sel.designation,"aaaa");
        }   
    }
}