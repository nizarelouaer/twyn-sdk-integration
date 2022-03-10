<p align="center">
  <img height="100" src="https://user-images.githubusercontent.com/57862776/157423531-a9e0f76e-9e93-4928-8d05-4f38254cedb7.png" alt="Twyn">
</p>

<p align="center">
    <b>Your digital SSI</b>
</p>

<p align=center>
    <a href="./docs/license/LICENSE.txt"><img src="https://img.shields.io/badge/License-Apache%202.0-success" alt="Apache 2.0 License"></a>
    <a href="./docs/roadmap/README.md"><img src="https://img.shields.io/badge/Roadmap-v1.0-bc1439.svg" alt="Roadmap v1.0"></a>
</p>

## Twyn SDK Integration

Twyn SDK is the [T4ISB](https://www.t4isb.com) mobile SDK that allows the enrollment as well as the identity validation of a Twyn user.

Our aim is to protect the user identity and give him the total control of his biometric data and SSI identity.

### Self Sovereign Identity (SSI)

As defined by [WIKIPEDIA](https://en.wikipedia.org/wiki/Self-sovereign_identity), SSI is an approach to digital identity that gives individuals control of their digital identities. We want to help people protect their data and their identity.

### Enroll Person Face
Twyn SDK provides an activity to allow the person face enrollment. The client app has to implement **EnrollFaceListener** to be able to get the **EnrollFaceResult**.
EnrollFaceResult has two atributes:<br/>
1. **_livenessScore_**: face enrollment checks the face liveness and returns a score between 0 and 100. The score **100** is a 100% live face and **0** is a fake one
2. **_croppedFace_**: face enrollment returns a cropped face (bitmap format) so that the client app can use it for verification/identitfication purposes

Below an example of calling the enroll face activity (Kotlin verison)

```
FaceActivity.setFaceListener(this)
val myIntent = Intent(this@MainActivity, FaceActivity::class.java)
myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
myIntent.addFlags(Intent.FLAG_ACTIVITY_FORWARD_RESULT)

startActivity(myIntent)
```
Once the face is enrolled, the client app will receive the result overriding the onEnrollFaceCompleted function:

```
    override fun onEnrollFaceCompleted(enrollFaceResult: EnrollFaceResult?) {
        if (enrollFaceResult != null) {
            runOnUiThread {
                Toast.makeText(
                    this@MainActivity,
                    "Successful Face Enrollment. Liveness Score ${enrollFaceResult.livenessScore}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
    
```

### Enroll Person Fingerprints
Twyn SDK provides an activity to allow the person fingerprints enrollment using the phone camera. The client app has to implement **EnrollFingerListener** to be able to get the **EnrollFingersResult**.
EnrollFingersResult has three atributes:<br/>
1. **_leftHandScore_**: A **0-100** Quality score of the left hand. 
2. **_rightHandScore_**: A **0-100** Quality score of the right hand. 
3. **_fingersMap_**: map containing the images of the enrolled fingerprints

Below an example of calling the enroll fingers activity (Kotlin verison)

```
FingerActivity.setEnrollFingerListener(this)
val myIntent = Intent(this@MainActivity, FingerActivity::class.java)
myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
myIntent.addFlags(Intent.FLAG_ACTIVITY_FORWARD_RESULT)
startActivity(myIntent)

```
Once the fingers are enrolled, the client app will receive the result overriding the onEnrollFingerCompleted function:

```
   override fun onEnrollFingerCompleted(enrollFingersResult: EnrollFingersResult) {
        val keys: Set<*> = enrollFingersResult.fingersMap.keys
        println("keys $keys")

        val i = keys.iterator()
        while (i.hasNext()) {
            val key = i.next() as String
            println("key $key")
            val value = enrollFingersResult.fingersMap[key]
            println("value $value")

        }

        runOnUiThread {
            Toast.makeText(
                this@MainActivity,
                "Left Hand Score  ${enrollFingersResult.leftHandScore} \n Right Hand Score ${enrollFingersResult.rightHandScore}",
                Toast.LENGTH_LONG
            ).show()
        }
    }
    
```



For more details please see the provided integration example.


### Support or Contact

Having trouble with Pages?  [contact support](https://www.t4isb.com) and we’ll help you sort it out.


## Contributors ✨

Thanks to the people who contributed to Twyn SDK:

<!-- ALL-CONTRIBUTORS-LIST:START - Do not remove or modify this section -->
<!-- prettier-ignore-start -->
<!-- markdownlint-disable -->
<table>
  <tr>
    <td align="center"><a href="https://github.com/nizarelouaer"><img src="https://avatars.githubusercontent.com/u/57862776?v=4" width="50px;" alt=""/><br /><sub><b>Nizar Elouaer</b></sub></a><br /><a href="https://github.com/nizarelouaer/twyn-sdk-integration/commits?author=nizarelouaer" title="Code">💻</a></td>
    
  <tr>
   
  </tr>
</table>

<!-- markdownlint-restore -->
<!-- prettier-ignore-end -->

<!-- ALL-CONTRIBUTORS-LIST:END -->

## License

Twyn SDK is licensed under the Apache License, Version 2.0. View a copy of the [License file](LICENSE).
