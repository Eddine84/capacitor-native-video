import Foundation

@objc public class NativeVideo: NSObject {
    @objc public func echo(_ value: String) -> String {
        print(value)
        return value
    }
}
