package om.prabhu.presto.sql.function;
import com.google.common.collect.ImmutableSet;
import io.prestosql.spi.Plugin;

import java.util.Set;

public class HiveUdfFunctionsPlugin implements Plugin{
	
	public Set<Class<?>> getFunctions()
    {
        return ImmutableSet.<Class<?>>builder()
                .add(MaskCardNumber.class)
              
                .build();
    }

}
