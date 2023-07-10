use alive
db.createCollection("user_auth")

db.user_auth.insertOne({
    user_id: 1,
    username: "test",
    password: "test",
    email: "test@",
    check_code: "",
    status: 1,
    code_update_time: ISODate("2023-06-26T22:12:58Z")
})

db.createCollection("user_info")

db.user_info.insertOne({
    user_id: 1,
    nickname: "test",
    phone: null,
    gender: null
})


db.createCollection("main_record")

db.main_record.insertOne({
    user_id: 1,
    height: null,
    weight: null,
    exercise_time: null,
    calorie_in: null,
    calorie_consume: null,
    sleep_time: null,
    pressure: null,
    heart_rate: null,
    health_score: null,
    health_advice: null,
    update_time: ISODate("2023-06-26T22:13:02Z"),
    record_id: 1
})

db.createCollection("weight")
db.weight.insertMany([
    {
        user_id: 1,
        year_id: 2023,
        detail_value: {
            items: [
                { date: "2023-06-10", value: 59.1 },
                { date: "2023-07-10", value: 56 }
            ]
        }
    },
    {
        user_id: 1,
        year_id: 2022,
        detail_value: {
            items: [
                { date: "2022-06-10", weight: 59.1 }
            ]
        }
    }
])

db.createCollection("sleep_detail")

db.sleep_detail.insertOne({
    user_id: 1,
    date: ISODate("2023-06-26T22:12:58Z"),
    detail_value: "{\"awake_count\":0,\"sleep_awake_duration\":0,\"bedtime\":1687454880,\"sleep_deep_duration\":108,\"sleep_light_duration\":304,\"sleep_rem_duration\":81,\"duration\":493,\"items\":[{\"end_time\":1687455660,\"state\":3,\"start_time\":1687454880},{\"end_time\":1687458420,\"state\":2,\"start_time\":1687455660},{\"end_time\":1687459920,\"state\":3,\"start_time\":1687458420},{\"end_time\":1687460220,\"state\":4,\"start_time\":1687459920},{\"end_time\":1687460640,\"state\":3,\"start_time\":1687460220},{\"end_time\":1687461240,\"state\":4,\"start_time\":1687460640},{\"end_time\":1687461420,\"state\":3,\"start_time\":1687461240},{\"end_time\":1687461720,\"state\":4,\"start_time\":1687461420},{\"end_time\":1687461900,\"state\":3,\"start_time\":1687461720},{\"end_time\":1687462680,\"state\":2,\"start_time\":1687461900},{\"end_time\":1687462920,\"state\":3,\"start_time\":1687462680},{\"end_time\":1687463820,\"state\":2,\"start_time\":1687462920},{\"end_time\":1687464300,\"state\":3,\"start_time\":1687463820},{\"end_time\":1687465560,\"state\":2,\"start_time\":1687464300},{\"end_time\":1687468320,\"state\":3,\"start_time\":1687465560},{\"end_time\":1687468740,\"state\":4,\"start_time\":1687468320},{\"end_time\":1687471620,\"state\":3,\"start_time\":1687468740},{\"end_time\":1687472400,\"state\":2,\"start_time\":1687471620},{\"end_time\":1687474380,\"state\":3,\"start_time\":1687472400},{\"end_time\":1687474920,\"state\":4,\"start_time\":1687474380},{\"end_time\":1687475040,\"state\":3,\"start_time\":1687474920},{\"end_time\":1687475760,\"state\":4,\"start_time\":1687475040},{\"end_time\":1687480260,\"state\":3,\"start_time\":1687475760},{\"end_time\":1687481340,\"state\":4,\"start_time\":1687480260},{\"end_time\":1687482360,\"state\":3,\"start_time\":1687481340},{\"end_time\":1687482960,\"state\":4,\"start_time\":1687482360},{\"end_time\":1687483020,\"state\":3,\"start_time\":1687482960},{\"end_time\":1687483320,\"state\":4,\"start_time\":1687483020},{\"end_time\":1687484460,\"state\":3,\"start_time\":1687483320}],\"date_time\":1687478400,\"timezone\":32,\"wake_up_time\":1687484460}",
    length: 496
})
