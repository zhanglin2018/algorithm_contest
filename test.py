from copy import deepcopy
import numpy as np
import pulp as lp

def hello():
	return 'hello'

def bag_program(weights, nums, max_weight):
    """
    ????,?????????????
    input:
        weights: lengths array
        nums: numbers array
        max_weight: pipe length
    output:
        program
    """
    
    weight_num = [[]] * max_weight

    for il, l in enumerate(weights):
        for i in range(max_weight):
            if weight_num[i]:
                if i + l < max_weight:
                    vals = deepcopy(weight_num[i])
                    vals_ = []
                    for val in vals:
                        val[il] += 1
                        if val[il] <= nums[il]:
                            vals_.append(val)
                    tmp = deepcopy(weight_num[i + l])
                    tmp.extend(vals_)
                    weight_num[i + l] = tmp
        val = np.zeros(len(weights))
        for i in range(l, max_weight + 1, l):
            val[il] += 1
            if val[il] > nums[il]:
                break
            tmp = deepcopy(weight_num[i - 1])
            tmp.append(deepcopy(val))
            weight_num[i - 1] = tmp
    return weight_num[-weights[0]:]


def integer_program(num_arrs, nums):
    """
    input:
        num_arrs: num set
        nums: minimize num
    output:
        result
    """
    num_arrays_list = []
    for vals in num_arrs:
        num_arrays_list.extend(vals)

    nums_mat = np.array(num_arrays_list)
    print("\t", nums_mat.shape)

    A = nums_mat.T
    b = nums

    prob = lp.LpProblem("The GY Problem", lp.LpMinimize)
    x = [lp.LpVariable("x_%06d" % i, lowBound=0, cat="Integer")
         for i in range(A.shape[1])]

    prob += lp.lpSum(x), "Total Number"
    for i in range(len(b)):
        prob += lp.lpSum([A[i][j] * x[j] for j in range(A.shape[1])]) >= b[i], "lb%04d" % weights_array[i]
    prob.solve()
    print("\tStatus:", lp.LpStatus[prob.status])

    res = []
    for v in prob.variables():
        if v.varValue:
            res.append((A[:, int(v.name[2:])], v.varValue))
    return res


weights_array = np.array(
    [26, 30, 40, 56, 60, 76, 80, 86, 90, 100, 110, 116, 120, 176, 180, 200])
nums_array = np.array(
    [2, 10, 8, 16, 80, 6, 32, 4, 36, 4, 10, 8, 18, 40, 16, 8])
bag_weight = 600

weight_num_arrays = bag_program(weights_array, nums_array, bag_weight)

bags_num_min = np.ceil(sum(weights_array * nums_array) / bag_weight)
print("????", bags_num_min)


for i in range(1, len(weight_num_arrays) + 1):
    print(i)
    # ?????0,????????
    if not weight_num_arrays[-i]:
        continue
    result = integer_program(weight_num_arrays[-i:], nums_array)
    bags_num = sum([n[-1] for n in result])
    print("\t", bags_num)
    # ??????????,?????????
    if bags_num == bags_num_min:
        break
print("????", sum([n for _, n in result]))
