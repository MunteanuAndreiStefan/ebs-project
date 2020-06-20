import os
import random
import sys

# CHANGED BY USER
company_r = ["Ford", "AT&T", "Apple", "Walmart", "Amazon", "Chevron", "Verizon", "Boeing", "Dell", "IBM", "Intel", "Verizon"]
date_r = ["2020.04.14", "2020.05.08", "2020.08.07", "2020.08.07", "2020.09.21", "2020.09.28"]

number_of_total_messages = 10
weights_on_subscribers = [0.6,0.7,0.8,0.5,0.9]
weights_on_subscribers_equal = 0.6

file_name_publications = "publications.txt"
file_name_subscribers = "subscribers.txt"
# CHANGED BY USER

def generate_publications():
    result = {}

    result["company"] = random.choice(company_r)
    result["value"] = round(random.uniform(10.5 , 100.5), 2)
    result["drop"] = round(random.uniform(1.1, 15.1), 2)
    result["variation"] = round(random.uniform(0.1, 1.1), 2)
    result["date"] = random.choice(date_r)

    #f = open(file_name_publications, "a")
    #f.write(str(result)+"\n")
    print("P-" + str(result)+"\n")
    #f.close()

    return 0


def generate_subscribers(array_chance, nr_equals):
    result_subs = []
    # Company
    c1 = random.choices(
        population=[random.choice(company_r), None],
        weights=[array_chance[0], 1-array_chance[0]],
        k=1
    )
    if c1[0] is not None:
        c1_res = ["company", weighted_signs(nr_equals), c1[0]]
        result_subs.append(c1_res)
    # Value
    c2 = random.choices(
        population=[round(random.uniform(10.5 , 100.5), 2), None],
        weights=[array_chance[1], 1 - array_chance[1]],
        k=1
    )
    if c2[0] is not None:
        c2_res = ["value", weighted_signs(nr_equals), c2[0]]
        result_subs.append(c2_res)
    # Drop
    c3 = random.choices(
        population=[round(random.uniform(1.1, 15.1), 2), None],
        weights=[array_chance[2], 1 - array_chance[2]],
        k=1
    )
    if c3[0] is not None:
        c3_res = ["drop", weighted_signs(nr_equals), c3[0]]
        result_subs.append(c3_res)
    # Variation
    c4 = random.choices(
        population=[round(random.uniform(0.1, 1.1)), None],
        weights=[array_chance[3], 1 - array_chance[3]],
        k=1
    )
    if c4[0] is not None:
        c4_res = ["variation", weighted_signs(nr_equals), c4[0]]
        result_subs.append(c4_res)
    # Date
    c5 = random.choices(
        population=[random.choice(date_r), None],
        weights=[array_chance[4], 1 - array_chance[4]],
        k=1
    )
    if c5[0] is not None:
        c5_res = ["date", weighted_signs(nr_equals), c5[0]]
        result_subs.append(c5_res)

    #f = open(file_name_subscribers, "a")
    #f.write(str(result_subs) + "\n")
    print("S-" + str(result_subs) + "\n")
    #f.close()

    return 0


def weighted_signs(number):
    rest = 1 - number
    return random.choices(
        population=["=", "<", ">", "<=", ">="],
        weights=[number, rest, rest, rest, rest],
        k=1
    )[0]


def cleanup():
    if os.path.exists(file_name_publications):
        os.remove(file_name_publications)
    if os.path.exists(file_name_subscribers):
        os.remove(file_name_subscribers)


if __name__== "__main__":

    cleanup()

    for x in range(number_of_total_messages):
        generate_publications()
        generate_subscribers(weights_on_subscribers, weights_on_subscribers_equal)

    print("Done ! Look in files publications.txt and subscribers.txt for results. ")