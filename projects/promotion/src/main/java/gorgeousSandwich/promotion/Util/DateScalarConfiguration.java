package gorgeousSandwich.promotion.Util;

import graphql.language.StringValue;
import graphql.schema.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Date;

@Configuration
public class DateScalarConfiguration {
    @Bean
    public GraphQLScalarType dateScalar() {
        return new GraphQLScalarType.Builder().name("Date").description("Java Date").coercing(
                new Coercing<Date, String>() {
                    @Override
                    public String serialize(Object dataFetcherResult) throws CoercingSerializeException {
                        if (dataFetcherResult instanceof Date) {
                            return dataFetcherResult.toString();
                        } else {
                            throw new CoercingSerializeException("Expected a Date object.");
                        }
                    }

                    @Override
                    public Date parseValue(Object input) throws CoercingParseValueException {
                        try {
                            if (input instanceof String str) {
                                return new SimpleDateFormat("dd/MM/yyyy").parse(str);
                            } else {
                                throw new CoercingParseValueException("Expected a String");
                            }
                        } catch (Exception e) {
                            throw new CoercingParseValueException(String.format("Not a valid Long: '%s'.", input), e
                            );
                        }
                    }

                    @Override
                    public Date parseLiteral(Object input) throws CoercingParseLiteralException {
                        if (input instanceof StringValue str) {
                            try {
                                return new SimpleDateFormat("dd/MM/yyyy").parse(str.getValue());
                            } catch (Exception e) {
                                throw new CoercingParseLiteralException(e);
                            }
                        } else {
                            throw new CoercingParseLiteralException("Expected a StringValue.");
                        }
                    }

                }
        ).build();
    }
}
