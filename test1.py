from copy import deepcopy
import numpy as np

def hello():
	return 'hello'

def test():
    """
    ????,?????????????
    input:
        weights: lengths array
        nums: numbers array
        max_weight: pipe length
    output:
        program
    """
    
    
    weights = [6,3,2]
    nums = [1,2,4]
    max_weight = 10

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
