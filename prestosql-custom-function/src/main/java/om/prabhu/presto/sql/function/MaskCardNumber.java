package om.prabhu.presto.sql.function;
import io.airlift.slice.Slice;
import io.airlift.slice.Slices;
import io.prestosql.spi.function.Description;
import io.prestosql.spi.function.ScalarFunction;
import io.prestosql.spi.function.SqlType; 
import io.prestosql.spi.type.StandardTypes;
import javax.crypto.spec.SecretKeySpec;
import org.apache.cxf.common.util.Base64Utility;
import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;

public class MaskCardNumber {
	
	@ScalarFunction("maskcard")
    @Description("Converts encrpted card string to alternating masked card number")
    @SqlType(StandardTypes.VARCHAR)
    public static Slice mask(@SqlType(StandardTypes.VARCHAR) Slice encrptedCard,@SqlType(StandardTypes.VARCHAR) Slice key)
    {  
		 String maskedCardNumber="";
	     int cardLength=16;
	     StringBuilder sb=new StringBuilder();
		try {
			  //SecretKeySpec aesKey1 = ;
			  Cipher  decryptionCipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		      decryptionCipher.init(Cipher.DECRYPT_MODE,new SecretKeySpec(Base64Utility.decode(key.toStringUtf8()), "AES"));
		      byte[] decodedValue  = Base64Utility.decode( encrptedCard.toStringUtf8(), true);
		      byte[] unencryptedBytes  = decryptionCipher.doFinal(decodedValue);

		      String decryptedString = new String(unencryptedBytes, StandardCharsets.UTF_8);
		          cardLength = decryptedString.length();
		      String suffix = decryptedString.substring(cardLength - 4);
		      String prefix = decryptedString.substring(0, 6);
		      for(int i=0;i<cardLength-10;i++){
		    	  sb.append("X");
		      }
		      maskedCardNumber =prefix+sb+suffix;
	} catch (Exception e) {
		 for(int i=0;i<cardLength;i++){
	    	  sb.append("X");
	      }
		 maskedCardNumber=sb.toString();
	}
        //String argument = encrptedCard.toStringUtf8();
        return Slices.utf8Slice(maskedCardNumber);
    }

}
