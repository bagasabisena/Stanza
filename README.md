# Stanza

JSON-based chat protocol in Java which is very similiar to XMPP that I build for Rumpy chat application prototype. Actually this protocol just a simplification of XMPP re-formatted in JSON. In addition to that, I also add delivery and read receipt because those two features doesn't formally stated in the RFC (at the time of development).
Once again this protocol has a very basic functionality compared to XMPP and still in unfinished state.

## Example

Below are few examples of the exchanged message during communication

### Stream
Stream message is used to open two-way socket between server and client. Client send request to server

```json
{
	"stream": {
		"from": "bagas@rumpy.co/android1",
		"to": "server.rumpy.co",
		"user_agent": "android",
		"features": ["chat", "rich_presence"]
	}
}
```

and when negotiation success, theserver reply the message with stream ID. Stream ID is then used for communication using message, iq, and presence.
```json
{
	"stream": {
		"from": "server.rumpy.co",
		"to": "bagas@rumpy.co/android1",
		"id": "12345678"
	}
}
```

### Message
Message is client-to-client communication used to exchange string message from user A to user B via server. 
```json
{
	"message": {
		"from": "adiskaf@rumpy.co",
		"to": "bagas@rumpy.co",
		"id": "abc123",
		"type": "chat",
		"request": true,
		"body": "Hello!"
	}
}
```
`request=true` means that user A request for receipt once the message has been received (delivered receipt) and read (read receipt).
Once the message is received by user B, user B send delivered receipt contains the message ID of the received message.
```json
{
	"message": {
		"from": "bagas@rumpy.co",
		"to": "adiskaf@rumpy.co",
		"id": "def456",
		"received": ["abc123"]
	}
}
```
Read receipt is sent when user B has read the message. The receipt is similiar with delivered receipt but using `read` property instead of `received`.
```json
{
	"message": {
		"from": "bagas@rumpy.co",
		"to": "adiskaf@rumpy.co",
		"id": "def456",
		"read": ["abc123"]
	}
}
```

### IQ
Different with Message, IQ is used for client-server communication, analogous to HTTP.
In example below, client send IQ to search for particular user.
```json
{
	"iq": {
		"from": "adiskaf@rumpy.co",
		"to": "server.rumpy.co",
		"id": "ghi789",
		"type": "get",
		"content": "user",
		"query": "cempaka@rumpy.co"
	}
}
```
and this is the reply from server.
```json
{
	"iq": {
		"from": "server.rumpy.co",
		"to": "adiskaf@rumpy.co",
		"id": "jkl135",
		"type": "result",
		"content": "user",
		"query" : {
			"signum": "cempaka@rumpy.co",
			"fullname": "Cempaka",
			"presence": "sky is blue"
		}
	}
}
```

### Presence
Last type is presence. Presence is, well, the user presence, such as availability (online, idle, offline) and status updates. Below are examples of presence. In order to see user B presence, user A has to ask for user B permission.
```json
{ 
	"presence": {
		"to": "bagas@rumpy.co",
		"from": "adiskaf@rumpy.co",
		"type": "subscribe"
	}
}
```
```json
{ 
	"presence": {
		"to": "adiskaf@rumpy.co",
		"from" : "bagas@rumpy.co",
		"type": "subscribed"
	}
}
```


