load("//tools:build_rules/go.bzl", "go_package_library", "go_test")

package(default_visibility = ["//kythe:default_visibility"])

go_package_library(
    name = "xrefs",
    srcs = ["xrefs.go"],
    deps = [
        "//kythe/go/services/xrefs",
        "//kythe/go/storage/table",
        "//kythe/go/util/kytheuri",
        "//kythe/go/util/schema/edges",
        "//kythe/proto:common_proto_go",
        "//kythe/proto:graph_proto_go",
        "//kythe/proto:internal_proto_go",
        "//kythe/proto:serving_proto_go",
        "//kythe/proto:xref_proto_go",
        "@go_protobuf//:proto",
        "@go_snappy//:snappy",
        "@go_stringset//:stringset",
    ],
)

go_test(
    name = "xrefs_test",
    srcs = ["xrefs_test.go"],
    library = "xrefs",
    visibility = ["//visibility:private"],
    deps = [
        "//kythe/go/test/testutil",
        "@go_x_text//:encoding",
        "@go_x_text//:encoding/unicode",
        "@go_x_text//:transform",
    ],
)
