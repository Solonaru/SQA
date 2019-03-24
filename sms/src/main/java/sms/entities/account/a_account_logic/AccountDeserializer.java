package sms.entities.account.a_account_logic;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import sms.entities.account.Account;
import sms.entities.account.employee.Employee;

public class AccountDeserializer extends JsonDeserializer<Account> {

	public Account deserialize(JsonParser jp, DeserializationContext context) throws IOException {
		ObjectMapper mapper = (ObjectMapper) jp.getCodec();
		ObjectNode root = mapper.readTree(jp);

		return mapper.readValue(root.toString(), Employee.class);
	}
}