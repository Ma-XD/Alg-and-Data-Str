#include <iostream>
#include <vector>
#include <cmath>
#include <climits>

int cnt = 0;
const int INF = 1000000000;
void set(std::vector<int > &tree_min, int h, size_t i, size_t x, size_t lx, size_t rx) {
    if (rx - lx == 1) {
        tree_min[x] = h;
        return;
    }
    size_t m = (lx + rx) / 2;
    if (i < m) {
        set(tree_min, h, i, 2 * x + 1, lx, m);
    } else {
        set(tree_min, h, i, 2 * x + 2, m, rx);
    }
    tree_min[x] = std::min(tree_min[2 * x + 1], tree_min[2 * x + 2]);
}

void BOOM(std::vector<int > &tree_min, size_t l, size_t r, int p, size_t x, size_t lx, size_t rx) {
    if (l >=  rx || lx >= r || p < tree_min[x]) {
        return;
    }
    if (rx - lx == 1 && tree_min[x] <= p) {
        tree_min[x] = INF;
        cnt++;
        return;
    }
    size_t m = (lx + rx) / 2;
    BOOM(tree_min, l, r, p, 2 * x + 1, lx, m);
    BOOM(tree_min, l, r, p, 2 * x + 2, m, rx);
    tree_min[x] = std::min(tree_min[2 * x + 1], tree_min[2 * x + 2]);
}

void print(std::vector<int > &tree) {
    for (int i : tree) {
        std::cout << i << " ";
    }
    std::cout << "\n";
}

int main() {
    //std::ios::sync_with_stdio(false);
    //std::cin.tie(nullptr);
    //std::cout.tie(nullptr);


    size_t n, num_op;
    std::cin >> n >> num_op;
    size_t len = 1 << (static_cast<size_t>(log(n - 1) / log(2) + 1));
    std::vector<int > tree_min(len * 2 - 1, INF);
    for (size_t i = 0; i < num_op; i++) {
        int op;
        std::cin >> op;
        if (op == 1) {
            size_t index;
            int h;
            std::cin >> index >> h;
            set(tree_min, h, index,0, 0 , len);
        } else {
            size_t l, r;
            int p;
            std::cin >> l >> r >> p;
            BOOM(tree_min, l, r, p, 0, 0, len);
            std::cout << cnt << std::endl;
            cnt = 0;
        }
        //print(tree_min);
    }
    return 0;
}
