module.exports = {
    transform: {
      '^.+\\.[t|j]sx?$': 'babel-jest', // JSX와 JS 파일을 babel-jest로 변환
    },
    transformIgnorePatterns: [
        "/node_modules/",
        "\\.pnp\\.[^\\/]+$"
    ],
    moduleNameMapper: {
        '\\.(css|less|scss|sass|jpg|jpeg|png|gif|webp|svg)$': 'identity-obj-proxy' // 스타일 및 이미지 파일 모킹
    },
    testEnvironment: "jsdom",
    moduleDirectories: ["node_modules", "src"],
    moduleFileExtensions: ["js", "jsx"]
  };