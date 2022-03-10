# Twyn v1.0 Roadmap

Hi!
This document is our plan for Twyn development till its first enterprise-ready release. 

Goals of the release:

* **Make API stable** - ensure backward compatibility for at least one major version back.
  * Starting from the release, breaking changes in API should only be done with a proper deprecation notice
* **Achieve horizontal scalability** - distributed deployment able to serve billions of points
* **Easy integration** - make the user experience as smooth as possible
* **Resource efficiency** - push Twyn backend performance on the single machine to the limit

To build a solid foundation for future development, we decided to keep Twyn as legacy-free as possible.
That means that while switching to `v1.0`, some breaking changes are likely.


## How to contribute

If you are a Twyn user - Identity enthusiast, Data Scientist, ML Engineer, or MLOps, the best contribution would be the feedback on your experience with Twyn.
Let us know whenever you have a problem, face an unexpected behavior, or see a lack of documentation.
You can do it in any convenient way - create an [issue](https://github.com/Twyn/Twyn/issues), start a [discussion](https://github.com/Twyn/Twyn/discussions), or drop up a [message](https://www.t4isb.com).
If you use Twyn or Identity Learning in your projects, we'd love to hear your story! Feel free to share articles and demos in our community.

If you have problems with code or architecture understanding - reach us at any time.
Feeling confident and want to contribute more? - Come to [work with us](https://www.t4isb.com)!

## Milestones

* :earth_americas: iOS version
  * [ ] Release the iOS SDK version
  * [ ] Provide iOS integration Kit

---

* :electric_plug: Integration & Interfaces
  * [x] Provide on device Matching for fingerprints and faces
  * [x] Provide more functionalities for the SDK like quality scores and identity scores

---

* :gear: Payload Processing
  * [ ] Support storing data in an encrypted on device format
  * [ ] Support more identity transactions, e.g.
    * Remote identity verification
  * [ ] Support for `Blockchain`backend integration
  * [ ] Enable more types of biometric integrations, e.g.
    * Iris
    * Ear
    * Voice 

  
