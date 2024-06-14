package gorgeousSandwich.promotion.Util;

import graphql.language.StringValue;
import graphql.schema.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LongScalarConfiguration {
    @Bean
    public GraphQLScalarType longScalar() {
        return new GraphQLScalarType.Builder().name("Long").description("Java Long Value")
                .coercing(new Coercing<Long, String>() {
                    @Override
                    public String serialize(Object dataFetcherResult) throws CoercingSerializeException {
                        if (dataFetcherResult instanceof Long) {
                            return dataFetcherResult.toString();
                        } else {
                            throw new CoercingSerializeException("Expected a Long object.");
                        }
                    }

                    @Override
                    public Long parseValue(Object input) throws CoercingParseValueException {
                        try {
                            if (input instanceof String str) {
                                return Long.parseLong(str);
                            } else {
                                throw new CoercingParseValueException("Expected a String");
                            }
                        } catch (Exception e) {
                            throw new CoercingParseValueException(String.format("Not a valid Long: '%s'.", input), e
                            );
                        }
                    }

                    @Override
                    public Long parseLiteral(Object input) throws CoercingParseLiteralException {
                        if (input instanceof StringValue str) {
                            try {
                                return Long.parseLong(str.toString());
                            } catch (Exception e) {
                                throw new CoercingParseLiteralException(e);
                            }
                        } else {
                            throw new CoercingParseLiteralException("Expected a StringValue.");
                        }
                    }

                }).build();
    }


}
