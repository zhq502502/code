function SpryMap(param) {
    /**
     * Name:        MoveMap()
     * Description: Function that moves the map to a given X and Y offset.
     *              Note that the function takes into account locked edges in the
     *              map.
     * Parameters:  x - The new x offset of the map
     *              y - The new y offset of the map
     */
    function MoveMap(x, y) {
        var newX = x, newY = y;
        if(m.lockEdges) {
            var rightEdge = -m.map.offsetWidth + m.viewingBox.offsetWidth,
                topEdge = -m.map.offsetHeight + m.viewingBox.offsetHeight;
            newX = newX < rightEdge ? rightEdge : newX;
            newY = newY < topEdge ? topEdge : newY;
            newX = newX > 0 ? 0 : newX;
            newY = newY > 0 ? 0 : newY;
        }
        m.map.style.left = newX + "px";
        m.map.style.top = newY + "px";
    }
    
    /**
     * Name:        AddListener()
     * Description: Adds an event listener to the specified element.
     * Parameters:  element - The element for which the listener is being added
     *              event - The event for which the listener is being added
     *              f - The function being called each time that the event occurs
     */
    function AddListener(element, event, f) {
        if(element.attachEvent) {
            element["e" + event + f] = f;
            element[event + f] = function () {
                element["e" + event + f](window.event)
            };
            element.attachEvent("on" + event, element[event + f])
        } else element.addEventListener(event, f, false)
    }
    
    function Coordinate(startX, startY) {
        this.x = startX;
        this.y = startY;
    }
    
    var m = this;
    m.map = document.getElementById(param.id);
    m.width = typeof param.width == "undefined" ? 800 : param.width;
    m.height = typeof param.height == "undefined" ? 800 : param.height;
    m.scrolling = typeof param.scrolling == "undefined" ? true : param.scrolling;
    m.hoverCursor = param.hoverCursor || "auto";    // If you prefer, the "open hand" style is: "url(data:image/vnd.microsoft.icon;base64,AAACAAEAICACAAgACAAwAQAAFgAAACgAAAAgAAAAQAAAAAEAAQAAAAAAAAEAAAAAAAAAAAAAAgAAAAAAAAAAAAAA////AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAD8AAAA/AAAAfwAAAP+AAAH/gAAB/8AAA//AAAd/wAAGf+AAAH9gAADbYAAA2yAAAZsAAAGbAAAAGAAAAAAAAA//////////////////////////////////////////////////////////////////////////////////////gH///4B///8Af//+AD///AA///wAH//4AB//8AAf//AAD//5AA///gAP//4AD//8AF///AB///5A////5///8=), default"
    m.dragCursor = param.dragCursor || "url(data:image/vnd.microsoft.icon;base64,AAACAAEAICACAAcABQAwAQAAFgAAACgAAAAgAAAAQAAAAAEAAQAAAAAAAAEAAAAAAAAAAAAAAgAAAAAAAAAAAAAA////AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAD8AAAA/AAAAfwAAAP+AAAH/gAAB/8AAAH/AAAB/wAAA/0AAANsAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA//////////////////////////////////////////////////////////////////////////////////////gH///4B///8Af//+AD///AA///wAH//+AB///wAf//4AH//+AD///yT/////////////////////////////8=), default";
    m.scrollTime = typeof param.scrollTime == "undefined" ? 300 : param.scrollTime;
    m.lockEdges = typeof param.lockEdges == "undefined" ? true : param.lockEdges;
    m.viewingBox = document.createElement("div");
    if (typeof param.cssClass != "undefined") m.viewingBox.className = param.cssClass;
    m.viewingBox.style.cursor = m.hoverCursor;
    m.mousePosition = new Coordinate;
    m.mouseLocations = [];
    m.velocity = new Coordinate;
    m.mouseDown = false;
    m.timerId = -1;
    m.timerCount = 0;
    m.map.parentNode.replaceChild(m.viewingBox, m.map);
    m.viewingBox.appendChild(m.map);
    m.viewingBox.style.overflow = "hidden";
    m.viewingBox.style.width = m.width + "px";
    m.viewingBox.style.height = m.height + "px";
    m.viewingBox.style.position = "relative";
    m.map.style.position = "absolute";
    MoveMap(typeof param.startX == "undefined" ? 0 : -param.startX, typeof param.startY == "undefined" ? 0 : -param.startY);

    /**
     * Name:        MouseMove()
     * Description: Function called every time that the mouse moves
     */
    var MouseMove = function (b) {
        var e = b.clientX - m.mousePosition.x + parseInt(m.map.style.left),
            d = b.clientY - m.mousePosition.y + parseInt(m.map.style.top);
        MoveMap(e, d);
        m.mousePosition.x = b.clientX;
        m.mousePosition.y = b.clientY
    };

    /**
     * Name:        OnScrollTimer()
     * Description: Function called every time that the scroll timer fires
     */
    var OnScrollTimer = function () {
        if(m.mouseDown) {
            // Keep track of where the latest mouse location is
            m.mouseLocations.unshift(new Coordinate(m.mousePosition.x,
                                                    m.mousePosition.y));
            
            // Make sure that we're only keeping track of the last 10 mouse
            // clicks (just for efficiency)
            if(m.mouseLocations.length > 10)
                m.mouseLocations.pop();
        } else {
            
            var totalTics = m.scrollTime / 20;

            var fractionRemaining = (totalTics - m.timerCount) / totalTics;
            
            var xVelocity = m.velocity.x * fractionRemaining;
            var yVelocity = m.velocity.y * fractionRemaining;
            
            MoveMap(-xVelocity + parseInt(m.map.style.left),
                    -yVelocity + parseInt(m.map.style.top));

            // Only scroll for 20 calls of this function
            if(m.timerCount == totalTics) {
                clearInterval(m.timerId);
                m.timerId = -1
            }

            ++m.timerCount;
        }
    };

    /**
     * mousedown event handler
     */
    AddListener(m.viewingBox, "mousedown", function (e) {
        m.viewingBox.style.cursor = m.dragCursor;

        // Save the current mouse position so we can later find how far the
        // mouse has moved in order to scroll that distance
        m.mousePosition.x = e.clientX;
        m.mousePosition.y = e.clientY;

        // Start paying attention to when the mouse moves
        AddListener(document, "mousemove", MouseMove);
        m.mouseDown = true;

        // If the map is set to continue scrolling after the mouse is released,
        // start a timer for that animation
        if(m.scrolling) {
            m.timerCount = 0;

            if(m.timerId != 0)
            {
                clearInterval(m.timerId);
                m.timerId = 0;
            }
            
            m.timerId = setInterval(OnScrollTimer, 20);
        }
        
        e.preventDefault ? e.preventDefault() : e.returnValue = false;
    });

    /**
     * mouseup event handler
     */
    AddListener(document, "mouseup", function () {
        if(m.mouseDown) {
            var handler = MouseMove;
            if(document.detachEvent) {
                document.detachEvent("onmousemove", document["mousemove" + handler]);
                document["mousemove" + handler] = null;
            } else {
                document.removeEventListener("mousemove", handler, false);
            }
            
            m.mouseDown = false;
            
            if(m.mouseLocations.length > 0) {
                var clickCount = m.mouseLocations.length;
                m.velocity.x = (m.mouseLocations[clickCount - 1].x - m.mouseLocations[0].x) / clickCount;
                m.velocity.y = (m.mouseLocations[clickCount - 1].y - m.mouseLocations[0].y) / clickCount;
                m.mouseLocations.length = 0;
            }
        }
        
        m.viewingBox.style.cursor = m.hoverCursor;
    });
};