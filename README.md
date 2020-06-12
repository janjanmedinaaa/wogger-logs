# Wogger Logs
A Development Logger that sends notifications via [Messenger Chatbot](m.me/woggerlogs), SMS, or even a custom Webhook. Wogger helps you remotely debug issues on your Android Application through Webhook Events.

## Usage

### Setup Global Config
```kotlin
class MainActivity : AppCompatActivity() {

  companion object {
    const val TAG = "WOGGER_LOGS"

    const val WOGGER_MESSENGER_RECEIVER = ""
    const val WOGGER_SMS_RECEIVER = ""
    const val WOGGER_CUSTOM_WEBHOOK = ""
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    // Set the Global Config
    Wogger.apply {
      owner = this@MainActivity
      receiver = WOGGER_MESSENGER_RECEIVER
      platform = WoggerPlatform.MESSENGER
      webhook = WOGGER_CUSTOM_WEBHOOK
    }
  }
}
```

### Log and Send Notifications
```kotlin
// Simple Log Message
Wogger.d(TAG, "Test Line 30")

// Send the Log Message to Globally Set Platform and Receiver
Wogger.i(TAG, "Running on Line 33").send()

// Send the Log Message depending on a Condition
Wogger.e(TAG, "Bug on Line 25").send(send = BuildConfig.DEBUG)

// Send the Log Message with a Custom Platform and Receiver
Wogger.w(TAG, "Warn me through SMS").send(
  receiver = WOGGER_SMS_RECEIVER,
  platform = WoggerPlatform.SMS
)

// Send the Log Message to a Custom Webhook
Wogger.wtf(TAG, "Send this to my Webhook").send(
  platform = WoggerPlatform.WEBHOOK
)
```

## Methods 
- `send()`
  - receiver (Optional)
    - Sets whom to send the Log Notification. Can either be a Phone Number or a Wogger Key from the Messenger Chatbot.
    - Default value will be the Globally Set Receiver.s
  - platform (Optional)
    - Set where to send the Log Notification. Possible values should be WoggerPlatform.
    - Default value will be the Globally Set Platform.
  - send (Optional)
    - Send a Log Notification depending on a specific Boolean Value
    - Default value is `true`