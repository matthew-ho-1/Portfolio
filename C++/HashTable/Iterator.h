#pragma once

template <typename U>

class Iterator {
    public:
        virtual bool hasNext() = 0;
        virtual U* next() = 0;
};