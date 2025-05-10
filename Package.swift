// swift-tools-version: 5.9
import PackageDescription

let package = Package(
    name: "CapacitorNativeVideo",
    platforms: [.iOS(.v14)],
    products: [
        .library(
            name: "CapacitorNativeVideo",
            targets: ["NativeVideoPlugin"])
    ],
    dependencies: [
        .package(url: "https://github.com/ionic-team/capacitor-swift-pm.git", from: "7.0.0")
    ],
    targets: [
        .target(
            name: "NativeVideoPlugin",
            dependencies: [
                .product(name: "Capacitor", package: "capacitor-swift-pm"),
                .product(name: "Cordova", package: "capacitor-swift-pm")
            ],
            path: "ios/Sources/NativeVideoPlugin"),
        .testTarget(
            name: "NativeVideoPluginTests",
            dependencies: ["NativeVideoPlugin"],
            path: "ios/Tests/NativeVideoPluginTests")
    ]
)